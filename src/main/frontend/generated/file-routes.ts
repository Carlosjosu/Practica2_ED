import { createRoute as createRoute_1 } from "@vaadin/hilla-file-router/runtime.js";
import type { AgnosticRoute as AgnosticRoute_1 } from "@vaadin/hilla-file-router/types.js";
import * as Page_1 from "../views/@index.js";
import * as Layout_1 from "../views/@layout.js";
import * as Page_2 from "../views/album-list.js";
import * as Page_3 from "../views/artista-list.js";
import * as Page_4 from "../views/artistabanda-list.js";
import * as Page_5 from "../views/banda-list.js";
import * as Page_6 from "../views/cancion-list.js";
import * as Page_7 from "../views/genero-list.js";
import * as Page_8 from "../views/task-list.js";
const routes: readonly AgnosticRoute_1[] = [
    createRoute_1("", Layout_1, [
        createRoute_1("", Page_1),
        createRoute_1("album-list", Page_2),
        createRoute_1("artista-list", Page_3),
        createRoute_1("artistabanda-list", Page_4),
        createRoute_1("banda-list", Page_5),
        createRoute_1("cancion-list", Page_6),
        createRoute_1("genero-list", Page_7),
        createRoute_1("task-list", Page_8)
    ])
];
export default routes;
