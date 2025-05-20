import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, DatePicker, Grid, GridColumn, TextField } from '@vaadin/react-components';
import { Notification } from '@vaadin/react-components/Notification';

import { useSignal } from '@vaadin/hilla-react-signals';
import handleError from 'Frontend/views/_ErrorHandler';
import { Group, ViewToolbar } from 'Frontend/components/ViewToolbar';

import { useDataProvider } from '@vaadin/hilla-react-crud';
import { ArtistaService, CancionService } from 'Frontend/generated/endpoints';
import Artista from 'Frontend/generated/com/unl/estrdts/base/models/Artista';

export const config: ViewConfig = {
  title: 'Artista',
  menu: {
    icon: 'vaadin:clipboard-check',
    order: 4,
    title: 'Artista',
  },
};

const dateFormatter = new Intl.DateTimeFormat(undefined, { 
  dateStyle: 'medium',
});



export default function ArtistaLisView() {
  const dataProvider = useDataProvider<Artista>({
    list: () => ArtistaService.lisAllArtista(),
  });

  return (
    <main className="w-full h-full flex flex-col box-border gap-s p-m">
      <ViewToolbar title="Artistas">
        <Group>
         
        </Group>
      </ViewToolbar>
      <Grid dataProvider={dataProvider.dataProvider}>
        <GridColumn path="nombres" header="Nombre" />
        <GridColumn path="nacionalidad" header="Nacionalidad" />
      </Grid>
    </main>
  );
}
