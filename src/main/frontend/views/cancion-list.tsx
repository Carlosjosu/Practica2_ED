import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, ComboBox, Dialog, Grid, GridColumn, GridItemModel, TextField, VerticalLayout } from '@vaadin/react-components';
import { Notification } from '@vaadin/react-components/Notification';
import { useSignal } from '@vaadin/hilla-react-signals';
import handleError from 'Frontend/views/_ErrorHandler';
import { Group, ViewToolbar } from 'Frontend/components/ViewToolbar';
import { useDataProvider } from '@vaadin/hilla-react-crud';
import { CancionServices, GeneroService, AlbumService } from 'Frontend/generated/endpoints';
import { useEffect, useState } from 'react';
import Genero from 'Frontend/generated/com/unl/estrdts/base/models/Genero';
import Cancion from 'Frontend/generated/com/unl/estrdts/base/models/Cancion';
import Album from 'Frontend/generated/com/unl/estrdts/base/models/Album';

export const config: ViewConfig = {
  title: 'Cancion',
  menu: {
    icon: 'vaadin:music',
    order: 3,
    title: 'Cancion',
  },
};

type CancionEntryFormProps = {
  onCancionCreated?: () => void;
  albumes: Album[];
};

function CancionEntryForm(props: CancionEntryFormProps) {
  const dialogOpened = useSignal(false);

  // Señales para los campos
  const nombre = useSignal('');
  const url = useSignal('');
  const tipo = useSignal('');
  const genero = useSignal('');
  const album = useSignal('');

  // Señales para los items de los ComboBox
  const tipos = useSignal<string[]>([]);
  const generos = useSignal<{ value: string, label: string }[]>([]);
  const albumes = useSignal<{ value: string, label: string }[]>([]);

  useEffect(() => {
    CancionServices.listTipo()
      .then((result) => { tipos.value = (result || []).filter((tipo): tipo is string => tipo !== undefined); })
      .catch(console.error);
    GeneroService.listAllGenero()
      .then((result) => {
        generos.value = (result ?? [])
          .filter((g): g is Genero => !!g && typeof g === 'object')
          .map(g => ({ value: String(g.id), label: g.nombre }));
      })
      .catch(console.error);
    albumes.value = props.albumes.map(a => ({ value: String(a.id), label: a.nombre }));
  }, [props.albumes]);

  const createCancion = async () => {
    try {
      if (
        nombre.value.trim().length > 0 &&
        url.value.trim().length > 0 &&
        tipo.value.trim().length > 0 &&
        genero.value &&
        album.value
      ) {
        await CancionServices.create(
          nombre.value,
          parseInt(genero.value),
          0, // Duración eliminada, se envía 0 o ajusta según tu backend
          url.value,
          tipo.value,
          parseInt(album.value)
        );
        if (props.onCancionCreated) props.onCancionCreated();
        nombre.value = '';
        url.value = '';
        tipo.value = '';
        genero.value = '';
        album.value = '';
        dialogOpened.value = false;
        Notification.show('Canción creada exitosamente', { duration: 5000, position: 'bottom-end', theme: 'success' });
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
        headerTitle="Registrar Canción"
        opened={dialogOpened.value}
        onOpenedChanged={({ detail }) => { dialogOpened.value = detail.value; }}
        footer={
          <>
            <Button onClick={() => (dialogOpened.value = false)}>Cancelar</Button>
            <Button onClick={createCancion} theme="primary">Registrar</Button>
          </>
        }
      >
        <VerticalLayout style={{ alignItems: 'stretch', width: '18rem', maxWidth: '100%' }}>
          <TextField label="Nombre" value={nombre.value} onValueChanged={evt => (nombre.value = evt.detail.value)} />
          <TextField label="Url" value={url.value} onValueChanged={evt => (url.value = evt.detail.value)} />
          <ComboBox
            label="Género"
            items={generos.value}
            itemLabelPath="label"
            itemValuePath="value"
            value={genero.value}
            onValueChanged={e => (genero.value = e.detail.value)}
            placeholder="Seleccione género"
          />
          <ComboBox
            label="Tipo de archivo"
            items={tipos.value}
            value={tipo.value}
            onValueChanged={e => (tipo.value = e.detail.value)}
            placeholder="Seleccione tipo de archivo"
          />
          <ComboBox
            label="Álbum"
            items={albumes.value}
            itemLabelPath="label"
            itemValuePath="value"
            value={album.value}
            onValueChanged={e => (album.value = e.detail.value)}
            placeholder="Seleccione álbum"
          />
        </VerticalLayout>
      </Dialog>
      <Button onClick={() => (dialogOpened.value = true)}>Registrar</Button>
    </>
  );
}

function CancionEditDialog({ open, onClose, cancion, onSaved, generos, tipos, albumes }: {
  open: boolean, onClose: () => void, cancion: Cancion | null, onSaved: () => void,
  generos: Genero[], tipos: string[], albumes: Album[]
}) {
  const [nombre, setNombre] = useState('');
  const [url, setUrl] = useState('');
  const [tipo, setTipo] = useState('');
  const [genero, setGenero] = useState<string | undefined>(undefined);
  const [album, setAlbum] = useState<string | undefined>(albumes[0]?.id ? String(albumes[0].id) : undefined);

  useEffect(() => {
    if (cancion) {
      setNombre(cancion.nombre || '');
      setUrl(cancion.url || '');
      setTipo(cancion.tipo || '');
      setGenero(cancion.id_genero !== undefined ? String(cancion.id_genero) : undefined);
      setAlbum(cancion.id_album !== undefined ? String(cancion.id_album) : albumes[0]?.id ? String(albumes[0].id) : undefined);
    }
  }, [cancion, albumes]);

  const handleSave = async () => {
    if (!cancion) return;
    try {
      await CancionServices.update(
        cancion.id,
        nombre,
        parseInt(genero || '1'),
        0, 
        url,
        tipo,
        parseInt(album || '1')
      );
      Notification.show('Canción actualizada', { duration: 4000, position: 'bottom-end', theme: 'success' });
      onSaved();
      onClose();
    } catch (error) {
      handleError(error);
    }
  };

  return (
    <Dialog
      opened={open}
      onOpenedChanged={e => { if (!e.detail.value) onClose(); }}
      headerTitle="Editar Canción"
      footer={
        <>
          <Button onClick={onClose}>Cancelar</Button>
          <Button theme="primary" onClick={handleSave}>Guardar</Button>
        </>
      }
    >
      <VerticalLayout style={{ alignItems: 'stretch', width: '18rem', maxWidth: '100%' }}>
        <TextField label="Nombre" value={nombre} onValueChanged={e => setNombre(e.detail.value)} />
        <TextField label="Url" value={url} onValueChanged={e => setUrl(e.detail.value)} />
        <ComboBox
          label="Género"
          items={generos.map(g => ({ label: g.nombre, value: String(g.id) }))}
          itemLabelPath="label"
          itemValuePath="value"
          value={genero}
          onValueChanged={e => setGenero(e.detail.value)}
          placeholder="Seleccione género"
        />
        <ComboBox
          label="Tipo de archivo"
          items={tipos}
          value={tipo}
          onValueChanged={e => setTipo(e.detail.value)}
          placeholder="Seleccione tipo de archivo"
        />
        <ComboBox
          label="Álbum"
          items={albumes.map(a => ({ label: a.nombre, value: String(a.id) }))}
          itemLabelPath="label"
          itemValuePath="value"
          value={album}
          onValueChanged={e => setAlbum(e.detail.value)}
          placeholder="Seleccione álbum"
        />
      </VerticalLayout>
    </Dialog>
  );
}

export default function CancionListView() {
  const dataProvider = useDataProvider<Cancion>({
    list: async () => {
      const result = await CancionServices.listCancion();
      return (result ?? []).filter((cancion): cancion is Cancion => cancion !== undefined);
    },
  });

  const [generoMap, setGeneroMap] = useState<Record<number, string>>({});
  const [albumMap, setAlbumMap] = useState<Record<number, string>>({});
  const [albumes, setAlbumes] = useState<Album[]>([]);
  const [generos, setGeneros] = useState<Genero[]>([]);
  const [tipos, setTipos] = useState<string[]>([]);

  useEffect(() => {
    GeneroService.listAllGenero()
      .then((result) => {
        const generos = (result ?? []).filter((g): g is Genero => !!g && typeof g === 'object');
        const map: Record<number, string> = {};
        generos.forEach(g => { if (g.id !== undefined) map[g.id] = g.nombre ?? ''; });
        setGeneroMap(map);
        setGeneros(generos);
      })
      .catch(console.error);

    AlbumService.listAllAlbum()
      .then((result) => {
        const albumes = (result ?? []).filter((a): a is Album => !!a && typeof a === 'object');
        const map: Record<number, string> = {};
        albumes.forEach(a => { if (a.id !== undefined) map[a.id] = a.nombre ?? ''; });
        setAlbumMap(map);
        setAlbumes(albumes);
      })
      .catch(console.error);

    CancionServices.listTipo()
      .then((result) => setTipos((result || []).filter((tipo): tipo is string => tipo !== undefined)))
      .catch(console.error);
  }, []);

  const [editDialogOpen, setEditDialogOpen] = useState(false);
  const [selectedCancion, setSelectedCancion] = useState<Cancion | null>(null);

  function link({ item }: { item: Cancion }) {
    return (
      <span>
        <Button onClick={() => { setSelectedCancion(item); setEditDialogOpen(true); }}>
          Editar
        </Button>
      </span>
    );
  }
  function indexIndex({ model }: { model: GridItemModel<any> }) {
    return <span>{model.index + 1}</span>;
  }

  return (
    <main className="w-full h-full flex flex-col box-border gap-s p-m">
      <ViewToolbar title="Canciones">
        <Group>
          <CancionEntryForm onCancionCreated={() => dataProvider.refresh()} albumes={albumes} />
        </Group>
      </ViewToolbar>
      <Grid dataProvider={dataProvider.dataProvider}>
        <GridColumn renderer={indexIndex} header="ID" />
        <GridColumn path="nombre" header="Canción" />
        <GridColumn header="Género" renderer={({ item }) => generoMap[item.id_genero] || 'Sin género'} />
        <GridColumn header="Álbum" renderer={({ item }) => albumMap[item.id_album] || 'Sin álbum'} />
        {/* Elimina esta línea:
        <GridColumn header="Acciones" renderer={({ item }) => link({ item })} /> */}
      </Grid>
      {/* Elimina esta línea:
      <CancionEditDialog ... /> */}
    </main>
  );

} 
