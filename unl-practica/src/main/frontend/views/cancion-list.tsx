import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, ComboBox, DatePicker, Dialog, Grid, GridColumn, GridItemModel, NumberField, TextField, VerticalLayout } from '@vaadin/react-components';
import { Notification } from '@vaadin/react-components/Notification';
import { CancionService, ArtistaService,TaskService } from 'Frontend/generated/endpoints';
import { useSignal } from '@vaadin/hilla-react-signals';
import handleError from 'Frontend/views/_ErrorHandler';
import { Group, ViewToolbar } from 'Frontend/components/ViewToolbar';
//import Task from 'Frontend/generated/com/unl/pratica/taskmanagement/domain/Task';
import { useDataProvider } from '@vaadin/hilla-react-crud';
import { parsePath } from 'react-router';
import Cancion from 'Frontend/generated/com/unl/pratica/base/models/Cancion'
import { useCallback, useEffect, useState } from 'react';
import Artista from 'Frontend/generated/com/unl/pratica/base/models/Artista';

export const config: ViewConfig = {
  title: 'Canción',
  menu: {
    icon: 'vaadin:music',
    order: 1,
    title: 'Canción',
  },
};

type CancionEntryFormProps = {
  onCancionCreated?: () => void;
};

// CREAR CANCION
function CancionEntryForm(props: CancionEntryFormProps) {
  const nombre = useSignal('');
  const album = useSignal('');
  const genero = useSignal('');
  const url = useSignal('');
  const tipo = useSignal('');
  const duracion = useSignal('');
  const dialogOpened = useSignal(false);

  const createCancion = async () => {
    try {
      if ( nombre.value.trim() && genero.value.trim() && album.value.trim() && duracion.value.trim() 
        && url.value.trim() && tipo.value.trim()) {
        const id_genero = parseInt(genero.value) + 1;
        const id_album = parseInt(album.value) + 1;

        await CancionService.createCancion(
          nombre.value,
          id_genero,          
          parseInt(duracion.value),          
          url.value,
          tipo.value,
          id_album
          
        );

        props.onCancionCreated?.();

        nombre.value = '';
        album.value = '';
        genero.value = '';
        url.value = '';
        tipo.value = '';
        duracion.value = '';
        dialogOpened.value = false;

        Notification.show('Cancion creadacon exito', { duration: 5000, position: 'bottom-end', theme: 'success',
        });

      } else {
        Notification.show('No se pudo crear cancion, faltan datos', { duration: 5000, position: 'top-center', theme: 'error',
        });
      }

    } catch (error) {
      console.log(error);
      handleError(error);
    }
  };
 const listaGenero = useSignal<String[]>([]);
  useEffect(() => {
    CancionService.listGenero().then(data => 
      listaGenero.value = data
    ); 
  }, []);

  const listaAlbum = useSignal<String[]>([]);
  useEffect(() => {
    CancionService.listAlbumCombo().then(data => 
      listaAlbum.value = data);
  }, []);

  const listaTipo = useSignal<String[]>([]);
   useEffect(() => {
    CancionService.listTipo().then(data => 
      listaTipo.value = data); 
  }, []);


  return (
    <>
      <Dialog
        modeless
        headerTitle="Nueva cancion"
        opened={dialogOpened.value}
        onOpenedChanged={({ detail }) => {
          dialogOpened.value = detail.value;
        }}
        footer={
          <>
            <Button 
            onClick={() => 
              dialogOpened.value = false
              }
              >
                Cancelar
                </Button>
            <Button onClick={createCancion} theme="primary">
                Registrar
                </Button>
          </>
        }
      >
        <VerticalLayout style={{ alignItems: 'stretch', width: '18rem', maxWidth: '100%' }}>
          <TextField label="Nombre de la Cancion"
            placeholder="Ingrese el nombre de la cancion"
            value={nombre.value}
            onValueChanged={(evt) => nombre.value = evt.detail.value}
          />

          <ComboBox label="Album"
            items={listaAlbum.value}
            placeholder="Seleccione un Album"
            value={album.value}
            onValueChanged={(evt) => album.value = evt.detail.value}
          />

          <ComboBox label="Genero"
            items={listaGenero.value}
            placeholder="Seleccione un genero"
            value={genero.value}
            onValueChanged={(evt) => genero.value = evt.detail.value}
          />
      
           <TextField label="Url/Link de la cancion"
            placeholder="Ingrese el link/url de la cancion"
            value={url.value}
            onValueChanged={(evt) => url.value = evt.detail.value}
          />

          <ComboBox label="Tipo de archivo"
            items={listaTipo.value}
            placeholder="Seleccione tipo"
            value={tipo.value}
            onValueChanged={(evt) => tipo.value = evt.detail.value}
          />
          <NumberField label="duracion"
            placeholder="310(segundos)"
            value={duracion.value}
            onValueChanged={(evt) => duracion.value = evt.detail.value}
          />
         
        </VerticalLayout>
      </Dialog>
      <Button 
      onClick={() => 
        dialogOpened.value = true
        }
        >
          Agregar
          </Button>
    </>
  );
}



//Lista de Canciones
export default function CancionView() {

  const dataProvider = useDataProvider<Cancion>({
    list: () => CancionService.listCancion(),
  });

  function indexIndex({ model }: { model: GridItemModel<Cancion> }) {
    return (
      <span>
        {model.index + 1}
      </span>
    );
  }


  return (
    <main className="w-full h-full flex flex-col box-border gap-s p-m">
      <ViewToolbar title="Lista de canciones">
        <Group>
          <CancionEntryForm onCancionCreated={dataProvider.refresh} />
        </Group>
      </ViewToolbar>
      <Grid dataProvider={dataProvider.dataProvider}>
        <GridColumn renderer={indexIndex} header="Nro" />
        <GridColumn path="nombre" header="Cancion" />
        <GridColumn path="album" header="Album" />
        <GridColumn path="genero" header="Genero" />
        <GridColumn path="url" header="Link" />
        <GridColumn path="tipo" header="Tipo" />
        <GridColumn path="duracion" header="Duracion(s)" />

      </Grid>
    </main>
  );
}


