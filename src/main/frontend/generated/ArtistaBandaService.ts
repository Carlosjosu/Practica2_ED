import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Artista_Banda_1 from "./com/unl/estrdts/base/models/Artista_Banda.js";
import client_1 from "./connect-client.default.js";
async function lisAllArtista_1(init?: EndpointRequestInit_1): Promise<Array<Artista_Banda_1 | undefined> | undefined> { return client_1.call("ArtistaBandaService", "lisAllArtista", {}, init); }
async function listAll_1(init?: EndpointRequestInit_1): Promise<Array<Record<string, unknown> | undefined> | undefined> { return client_1.call("ArtistaBandaService", "listAll", {}, init); }
export { lisAllArtista_1 as lisAllArtista, listAll_1 as listAll };
