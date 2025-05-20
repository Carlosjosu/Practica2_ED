import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Artista_1 from "./com/unl/estrdts/base/models/Artista.js";
import client_1 from "./connect-client.default.js";
async function lisAllArtista_1(init?: EndpointRequestInit_1): Promise<Array<Artista_1 | undefined> | undefined> { return client_1.call("ArtistaService", "lisAllArtista", {}, init); }
export { lisAllArtista_1 as lisAllArtista };
