import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog, Grid, GridColumn, GridItemModel, TextField, VerticalLayout } from '@vaadin/react-components';
import { Notification } from '@vaadin/react-components/Notification';
import { GeneroService } from 'Frontend/generated/endpoints';
import { useSignal } from '@vaadin/hilla-react-signals';
import handleError from 'Frontend/views/_ErrorHandler';
import { Group, ViewToolbar } from 'Frontend/components/ViewToolbar';
import { useDataProvider } from '@vaadin/hilla-react-crud';

export const config: ViewConfig = {
  title: 'Genero',
  menu: {
    icon: 'vaadin:tag',
    order: 2,
    title: 'Genero',
  },
};

type GeneroEntryFormProps = {
  onGeneroCreated?: () => void;
};

function GeneroEntryForm(props: GeneroEntryFormProps) {
  const nombre = useSignal('');
  const dialogOpened = useSignal(false);

  const createGenero = async () => {
    try {
      if (nombre.value.trim().length > 0) {
        await GeneroService.createGenero(nombre.value);
        if (props.onGeneroCreated) props.onGeneroCreated();
        nombre.value = '';
        dialogOpened.value = false;
        Notification.show('Género creado exitosamente', { duration: 5000, position: 'bottom-end', theme: 'success' });
      } else {
        Notification.show('No se pudo crear, faltan datos', { duration: 5000, position: 'top-center', theme: 'error' });
      }
    } catch (error) {
      handleError(error);
    }
  };

  return (
    <>
      <Dialog
        modeless
        headerTitle="Nuevo género"
        opened={dialogOpened.value}
        onOpenedChanged={({ detail }: { detail: { value: boolean } }) => {
          dialogOpened.value = detail.value;
        }}
        footer={
          <>
            <Button onClick={() => (dialogOpened.value = false)}>Cancelar</Button>
            <Button onClick={createGenero} theme="primary">Registrar</Button>
          </>
        }
      >
        <VerticalLayout style={{ alignItems: 'stretch', width: '18rem', maxWidth: '100%' }}>
          <TextField
            label="Nombre del género"
            value={nombre.value}
            onValueChanged={(evt) => (nombre.value = evt.detail.value)}
          />
        </VerticalLayout>
      </Dialog>
      <Button onClick={() => (dialogOpened.value = true)}>Agregar</Button>
    </>
  );
}

export default function GeneroListView() {
  const dataProvider = useDataProvider<any>({
    list: async () => {
      const result = await GeneroService.listAllGenero();
      return (result ?? []).filter((item): item is Record<string, unknown> => item !== undefined);
    },
  });

  function indexIndex({ model }: { model: GridItemModel<any> }) {
    return <span>{model.index + 1}</span>;
  }

  return (
    <main className="w-full h-full flex flex-col box-border gap-s p-m">
      <ViewToolbar title="Lista de géneros">
        <Group>
          <GeneroEntryForm onGeneroCreated={dataProvider.refresh} />
        </Group>
      </ViewToolbar>
      <Grid dataProvider={dataProvider.dataProvider}>
        <GridColumn renderer={indexIndex} header="Nro" />
        <GridColumn path="nombre" header="Nombre del género" />
      </Grid>
    </main>
  );
}