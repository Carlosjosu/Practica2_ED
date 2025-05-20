import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, DatePicker, Dialog, Grid, GridColumn, GridItemModel, TextField, VerticalLayout } from '@vaadin/react-components';
import { Notification } from '@vaadin/react-components/Notification';

import { useSignal } from '@vaadin/hilla-react-signals';
import handleError from 'Frontend/views/_ErrorHandler';
import { Group, ViewToolbar } from 'Frontend/components/ViewToolbar';

import { useDataProvider } from '@vaadin/hilla-react-crud';
import { ArtistaBandaService, ArtistaService, CancionService } from 'Frontend/generated/endpoints';
import Artista from 'Frontend/generated/com/unl/estrdts/base/models/Artista';
import Artista_Banda from 'Frontend/generated/com/unl/estrdts/base/models/Artista_Banda';

/*export const config: ViewConfig = {
  title: 'ArtistaBanda',
  menu: {
    icon: 'vaadin:clipboard-check',
    order: 5,
    title: 'ArtistaBanda',
  },
};*/

type BandaEntryFormProps = {
  onBandaCreated?: () => void;
};

function BandaEntryForm(props: BandaEntryFormProps) {
  const dialogOpened = useSignal(false);

  const open = () => {
    dialogOpened.value = true;
  };

  const close = () => {
    dialogOpened.value = false;
  };

  const expresion = useSignal('');

  const createBanda = async () => {
      try {
        if (expresion.value.trim().length > 0) {
          if (props.onBandaCreated) {
            props.onBandaCreated();
          }
          expresion.value = '';
         
          dialogOpened.value = false;
          Notification.show('Banda creada exitosamente', { duration: 5000, position: 'bottom-end', theme: 'success' });
        } else {
          Notification.show('No se pudo crear, faltan datos', { duration: 5000, position: 'top-center', theme: 'error' });
        }
  
      } catch (error) {
        console.log(error);
        handleError(error);
      }
    };

  return (
    <>
      <Dialog
        aria-label="Registrar Banda"
        draggable
        modeless
        opened={dialogOpened.value}
        onOpenedChanged={(event) => {
          dialogOpened.value = event.detail.value;
        }}
        header={
          <h2
            className="draggable"
            style={{
              flex: 1,
              cursor: 'move',
              margin: 0,
              fontSize: '1.5em',
              fontWeight: 'bold',
              padding: 'var(--lumo-space-m) 0',
            }}
          >
            Registrar Banda
          </h2>
        }
        footerRenderer={() => (
          <>
            <Button onClick={close}>Cancelar</Button>
            <Button theme="primary" onClick={createBanda}>
              Registrar
            </Button>
          </>
        )}
      >
        <VerticalLayout
          theme="spacing"
          style={{ width: '300px', maxWidth: '100%', alignItems: 'stretch' }}
        >
          <VerticalLayout style={{ alignItems: 'stretch' }}>
            <TextField label="Nombre"
              placeholder='Ingrese el nombre de la banda'
              aria-label='Ingrese el nombre de la banda'
              value={expresion.value}
              onValueChanged={(evt) => (expresion.value = evt.detail.value)}
            />
            
          </VerticalLayout>
        </VerticalLayout>
      </Dialog>
      <Button onClick={open}>Registrar</Button>
    </>
  );
}

const dateFormatter = new Intl.DateTimeFormat(undefined, { 
  dateStyle: 'medium',
});

function index({ model }: { model: GridItemModel<any> }) {
  return (
    <span>
      {model.index + 1}
    </span>
  );
}

function link({ item }: { item: Artista_Banda }) {
  return (
    <span>
      <Button>
        Editar
      </Button>
    </span>
  );
}

export default function ArtistaBandaLisView() {
  const dataProvider = useDataProvider<any>({
    list: async () => (await ArtistaBandaService.listAll())?.filter(Boolean) ?? [],
  });

  return (
    <main className="w-full h-full flex flex-col box-border gap-s p-m">
      <ViewToolbar title="Bandas">
        <Group>
          <BandaEntryForm onBandaCreated={dataProvider.refresh} />
        </Group>
      </ViewToolbar>
      <Grid dataProvider={dataProvider.dataProvider}>
        <GridColumn header="Nro" renderer={index} />
        <GridColumn path="artista" header="Artista"/>
        <GridColumn path="banda" header="Banda"/> 
        <GridColumn path="rol" header="Rol"/>
        <GridColumn header="Acciones" renderer={link} />
      </Grid>
    </main>
  );
}
