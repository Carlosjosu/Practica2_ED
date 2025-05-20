import { _enum as _enum_1, EnumModel as EnumModel_1, makeEnumEmptyValueCreator as makeEnumEmptyValueCreator_1 } from "@vaadin/hilla-lit-form";
import RolArtistaEnum_1 from "./RolArtistaEnum.js";
class RolArtistaEnumModel extends EnumModel_1<typeof RolArtistaEnum_1> {
    static override createEmptyValue = makeEnumEmptyValueCreator_1(RolArtistaEnumModel);
    readonly [_enum_1] = RolArtistaEnum_1;
}
export default RolArtistaEnumModel;
