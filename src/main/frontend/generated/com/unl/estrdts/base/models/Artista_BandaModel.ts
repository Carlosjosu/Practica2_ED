import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type Artista_Banda_1 from "./Artista_Banda.js";
import RolArtistaEnumModel_1 from "./RolArtistaEnumModel.js";
class Artista_BandaModel<T extends Artista_Banda_1 = Artista_Banda_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(Artista_BandaModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get rol(): RolArtistaEnumModel_1 {
        return this[_getPropertyModel_1]("rol", (parent, key) => new RolArtistaEnumModel_1(parent, key, true));
    }
    get id_artista(): NumberModel_1 {
        return this[_getPropertyModel_1]("id_artista", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get id_banda(): NumberModel_1 {
        return this[_getPropertyModel_1]("id_banda", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
}
export default Artista_BandaModel;
