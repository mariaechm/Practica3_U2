import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Banda_1 from "./com/unl/practica/base/models/Banda.js";
import client_1 from "./connect-client.default.js";
async function createBanda_1(nombre: string | undefined, fecha: string | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("BandaServices", "createBanda", { nombre, fecha }, init); }
async function listAllBanda_1(init?: EndpointRequestInit_1): Promise<Array<Banda_1 | undefined> | undefined> { return client_1.call("BandaServices", "listAllBanda", {}, init); }
export { createBanda_1 as createBanda, listAllBanda_1 as listAllBanda };
