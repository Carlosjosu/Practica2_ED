/*
 * Copyright 2000-2025 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import './contextMenuConnector.js';

/**
 * Initializes the connector for a menu bar element.
 *
 * @param {HTMLElement} menubar
 * @param {string} appId
 */
function initLazy(menubar, appId) {
  if (menubar.$connector) {
    return;
  }

  const observer = new MutationObserver((records) => {
    const hasChangedAttributes = records.some((entry) => {
      const oldValue = entry.oldValue;
      const newValue = entry.target.getAttribute(entry.attributeName);
      return oldValue !== newValue;
    });

    if (hasChangedAttributes) {
      menubar.$connector.generateItems();
    }
  });

  menubar.$connector = {
    /**
     * Generates and assigns the items to the menu bar.
     *
     * When the method is called without providing a node id,
     * the previously generated items tree will be used.
     * That can be useful if you only want to sync the disabled and hidden properties of root items.
     *
     * @param {number | undefined} nodeId
     */
    generateItems(nodeId) {
      if (!menubar.shadowRoot) {
        // workaround for https://github.com/vaadin/flow/issues/5722
        setTimeout(() => menubar.$connector.generateItems(nodeId));
        return;
      }

      if (!menubar._container) {
        // Menu-bar defers first buttons render to avoid re-layout
        // See https://github.com/vaadin/web-components/issues/7271
        queueMicrotask(() => menubar.$connector.generateItems(nodeId));
        return;
      }

      if (nodeId) {
        menubar.__generatedItems = window.Vaadin.Flow.contextMenuConnector.generateItemsTree(appId, nodeId);
      }

      let items = menubar.__generatedItems || [];

      items.forEach((item) => {
        // Propagate disabled state from items to parent buttons
        item.disabled = item.component.disabled;

        // Saving item to component because `_item` can be reassigned to a new value
        // when the component goes to the overflow menu
        item.component._rootItem = item;
      });

      // Observe for hidden and disabled attributes in case they are changed by Flow.
      // When a change occurs, the observer will re-generate items on top of the existing tree
      // to sync the new attribute values with the corresponding properties in the items array.
      items.forEach((item) => {
        observer.observe(item.component, {
          attributeFilter: ['hidden', 'disabled'],
          attributeOldValue: true
        });
      });

      // Remove hidden items entirely from the array. Just hiding them
      // could cause the overflow button to be rendered without items.
      //
      // The items-prop needs to be set even when all items are visible
      // to update the disabled state and re-render buttons.
      items = items.filter((item) => !item.component.hidden);

      menubar.items = items;

      // Propagate click events from the menu buttons to the item components
      menubar._buttons.forEach((button) => {
        if (button.item && button.item.component) {
          button.addEventListener('click', (e) => {
            if (e.composedPath().indexOf(button.item.component) === -1) {
              button.item.component.click();
              e.stopPropagation();
            }
          });
        }
      });
    }
  };
}

function setClassName(component) {
  const item = component._rootItem || component._item;

  if (item) {
    item.className = component.className;
  }
}

window.Vaadin.Flow.menubarConnector = { initLazy, setClassName };
