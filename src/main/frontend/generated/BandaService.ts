import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Banda_1 from "./com/unl/estrdts/base/models/Banda.js";
import client_1 from "./connect-client.default.js";
async function createBanda_1(nombre: string | undefined, fechaCreacion: string | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("BandaService", "createBanda", { nombre, fechaCreacion }, init); }
async function lisAllBanda_1(init?: EndpointRequestInit_1): Promise<Array<Banda_1 | undefined> | undefined> { return client_1.call("BandaService", "lisAllBanda", {}, init); }
async function updateBanda_1(id: number | undefined, nombre: string | undefined, fechaCreacion: string | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("BandaService", "updateBanda", { id, nombre, fechaCreacion }, init); }
export { createBanda_1 as createBanda, lisAllBanda_1 as lisAllBanda, updateBanda_1 as updateBanda };
