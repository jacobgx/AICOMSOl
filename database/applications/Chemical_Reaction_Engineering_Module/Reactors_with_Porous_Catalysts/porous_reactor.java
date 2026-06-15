/*
 * porous_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:27 by COMSOL 6.3.0.290. */
public class porous_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Porous_Catalysts");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

    model.param().label("\u53c2\u6570 1\uff0c\u53cd\u5e94");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("kf1", "7.5e6[m^5/(s*mol^2)]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kr1", "2.9e10[m^2/(s*mol)]", "\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kf2", "6.3e5[m^5/(s*mol^2)]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kr2", "0[m^2/(s*mol)]", "\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kf3", "2.3e2[m^3/(s*mol)]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kr3", "4.0e9[1/s]", "\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kf4", "3.3e10[m^2/(s*mol)]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kr4", "2.2e12[m^2/(s*mol)]", "\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kf5", "5.5e13[m^2/(s*mol)]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kr5", "2.1e9[m^2/(s*mol)]", "\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kf6", "2.8e4[m^2/(s*mol)]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kr6", "1.5e-2[m^2/(s*mol)]", "\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("T_iso", "525[K]", "\u6e29\u5ea6");
    model.param().set("cat_area", "6e4[1/m]", "\u6bd4\u8868\u9762\u53cd\u5e94\u9762\u79ef");
    model.param().set("c0H2_inflow", "10[mol/m^3]", "\u6469\u5c14\u6d53\u5ea6\uff0c\u6d41\u5165");
    model.param().set("c0O2_inflow", "1[mol/m^3]", "\u6469\u5c14\u6d53\u5ea6\uff0c\u6d41\u5165");
    model.param().set("c0N2", "100[mol/m^3]", "\u6469\u5c14\u6d53\u5ea6");
    model.param().set("c0H_surf", "0[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8868\u9762\u7269\u8d28");
    model.param().set("c0H2O_surf", "0[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8868\u9762\u7269\u8d28");
    model.param().set("c0O_surf", "0[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8868\u9762\u7269\u8d28");
    model.param().set("c0OH_surf", "0[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8868\u9762\u7269\u8d28");
    model.param().set("c0Rh_surf", "2.7E-5[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8868\u9762\u7269\u8d28");

    model.component("comp1").physics("re").prop("energybalance").set("T", "T_iso");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "H2+2Rh(ads)<=>2H(ads)");
    model.component("comp1").physics("re").feature("rch1").set("kf", "kf1");
    model.component("comp1").physics("re").feature("rch1").set("kr", "kr1");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "O2+2Rh(ads)<=>2O(ads)");
    model.component("comp1").physics("re").feature("rch2").set("kf", "kf2");
    model.component("comp1").physics("re").feature("rch2").set("kr", "kr2");
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "H2O+Rh(ads)<=>H2O(ads)");
    model.component("comp1").physics("re").feature("rch3").set("kf", "kf3");
    model.component("comp1").physics("re").feature("rch3").set("kr", "kr3");
    model.component("comp1").physics("re").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch4").set("formula", "O(ads)+H(ads)<=>OH(ads)+Rh(ads)");
    model.component("comp1").physics("re").feature("rch4").set("kf", "kf4");
    model.component("comp1").physics("re").feature("rch4").set("kr", "kr4");
    model.component("comp1").physics("re").create("rch5", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch5").set("formula", "OH(ads)+H(ads)<=>H2O(ads)+Rh(ads)");
    model.component("comp1").physics("re").feature("rch5").set("kf", "kf5");
    model.component("comp1").physics("re").feature("rch5").set("kr", "kr5");
    model.component("comp1").physics("re").create("rch6", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch6").set("formula", "OH(ads)+OH(ads)<=>O(ads)+H2O(ads)");
    model.component("comp1").physics("re").feature("rch6").set("kf", "kf6");
    model.component("comp1").physics("re").feature("rch6").set("kr", "kr6");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "N2");
    model.component("comp1").physics("re").feature("N2").set("sType", "solvent");
    model.component("comp1").physics("re").prop("reactor").set("surfaceAreaOptions", "surfaceAreaToVolumeRatio");
    model.component("comp1").physics("re").prop("reactor").set("as", "cat_area");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c0H2_inflow", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c0N2", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c0O2_inflow", 3, 0);
    model.component("comp1").physics("re").feature("inits1").set("gamman0", 2.72E-5);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValues", "c0H_surf", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValues", "c0H2O_surf", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValues", "c0O_surf", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValues", "c0OH_surf", 3, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValues", "c0Rh_surf", 4, 0);

    model.study("std1").feature("time").set("tlist", "range(0,0.01,0.2)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_H2", "re.csurf_Rh_surf", "re.csurf_H_surf", "re.c_O2", "re.csurf_O_surf", "re.c_H2O", "re.csurf_H2O_surf", "re.csurf_OH_surf"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("\u6d53\u5ea6 (re)\uff0c\u672c\u4f53\u7269\u8d28");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_H2"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_H2", "re.c_H2O"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_H2", "re.c_H2O", "re.c_O2"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6d53\u5ea6 (re)\uff0c\u8868\u9762\u7269\u8d28");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{});
    model.result("pg2").feature("glob1").set("descr", new String[]{});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.csurf_H_surf"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u8868\u9762\u6d53\u5ea6"});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.csurf_H_surf", "re.csurf_O_surf"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"re.csurf_H_surf", "re.csurf_O_surf", "re.csurf_OH_surf"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"re.csurf_H_surf", "re.csurf_O_surf", "re.csurf_OH_surf", "re.csurf_H2O_surf"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"re.csurf_H_surf", "re.csurf_O_surf", "re.csurf_OH_surf", "re.csurf_H2O_surf", "re.csurf_Rh_surf"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6"});
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").run();

    model.component("comp1").physics("re").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "ReactingFlowPorousMedia");
    model.component("comp1").physics("re").feature("sync1").set("MultipleMassTransfer", "PorousCatalyst");
    model.component("comp1").physics("re").feature("sync1").set("study", "Transient");
    model.component("comp1").physics("re").prop("synchronize").set("synchronize", "1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 3);
    model.component("comp2").geom("geom1").geomRep("comsol");
    model.component("comp2").geom("geom1").axisymmetric(false);
    model.component("comp2").geom("geom1").label("\u51e0\u4f55 1(3D)");

    model.component("comp2").mesh().create("mesh1");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");

    model.component("comp1").physics("re").feature("sync1").set("genom", new String[]{"comp2:geom1"});
    model.component("comp1").physics("re").feature("sync1").set("studyname", new String[]{"comp2:std2"});
    model.component("comp2").physics().create("chem", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem", 0);
    model.component("comp2").physics().create("tds", "DilutedSpeciesInPorousCatalysts", "geom1");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T", "T_iso");
    model.component("comp2").physics("chem").prop("mixture").set("mixture", "gas");
    model.component("comp2").physics("chem").prop("mixture").set("gasDensitySel", "Automatic");
    model.component("comp2").physics("chem").prop("Activity").set("useActivity", "0");
    model.component("comp2").physics("chem").prop("chemkin").set("chemkin", "0");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("VolumetricConcentrationGlobalActivityStandardState", "1[mol/m^3]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceSpeciesConcentrationType", "SurfaceConcentration");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceGlobalActivityStandardState", "1[mol/m^2]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SpeciesrateUserDefinedList", new String[]{"N2"});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "9");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "4");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "5");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "5");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", "1");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventIsSet", "1");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventTag", "N2");
    model.component("comp2").physics("chem").create("rch1", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch1").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").feature("rch1").set("formula", "H2 + 2 Rh(ads) <=> 2 H(ads)");
    model.component("comp2").physics("chem").feature("rch1").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch1").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch1").set("kf", "kf1");
    model.component("comp2").physics("chem").feature("rch1").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch1").set("surfFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch1").set("setKeq0", "0");
    model.component("comp2").physics("chem").feature("rch1").set("kr", "kr1");
    model.component("comp2").physics("chem").feature("rch1").set("bulkRevOrder", "0");
    model.component("comp2").physics("chem").feature("rch1").set("surfRevOrder", "2");
    model.component("comp2").physics("chem").feature("rch1").label("1: \u8868\u9762: H2 + 2 Rh(ads) <=> 2 H(ads)");
    model.component("comp2").physics("chem").feature("rch1").set("rClass", "surface");
    model.component("comp2").physics("chem").create("rch2", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch2").set("rSequenceNo", "2");
    model.component("comp2").physics("chem").feature("rch2").set("formula", "O2 + 2 Rh(ads) <=> 2 O(ads)");
    model.component("comp2").physics("chem").feature("rch2").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch2").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch2").set("kf", "kf2");
    model.component("comp2").physics("chem").feature("rch2").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch2").set("surfFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch2").set("setKeq0", "0");
    model.component("comp2").physics("chem").feature("rch2").set("kr", "kr2");
    model.component("comp2").physics("chem").feature("rch2").set("bulkRevOrder", "0");
    model.component("comp2").physics("chem").feature("rch2").set("surfRevOrder", "2");
    model.component("comp2").physics("chem").feature("rch2").label("2: \u8868\u9762: O2 + 2 Rh(ads) <=> 2 O(ads)");
    model.component("comp2").physics("chem").feature("rch2").set("rClass", "surface");
    model.component("comp2").physics("chem").create("rch3", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch3").set("rSequenceNo", "3");
    model.component("comp2").physics("chem").feature("rch3").set("formula", "H2O + Rh(ads) <=> H2O(ads)");
    model.component("comp2").physics("chem").feature("rch3").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch3").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch3").set("kf", "kf3");
    model.component("comp2").physics("chem").feature("rch3").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch3").set("surfFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch3").set("setKeq0", "0");
    model.component("comp2").physics("chem").feature("rch3").set("kr", "kr3");
    model.component("comp2").physics("chem").feature("rch3").set("bulkRevOrder", "0");
    model.component("comp2").physics("chem").feature("rch3").set("surfRevOrder", "1");
    model.component("comp2").physics("chem").feature("rch3").label("3: \u8868\u9762: H2O + Rh(ads) <=> H2O(ads)");
    model.component("comp2").physics("chem").feature("rch3").set("rClass", "surface");
    model.component("comp2").physics("chem").create("rch4", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch4").set("rSequenceNo", "4");
    model.component("comp2").physics("chem").feature("rch4").set("formula", "O(ads) + H(ads) <=> OH(ads) + Rh(ads)");
    model.component("comp2").physics("chem").feature("rch4").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch4").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch4").set("kf", "kf4");
    model.component("comp2").physics("chem").feature("rch4").set("bulkFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch4").set("surfFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch4").set("setKeq0", "0");
    model.component("comp2").physics("chem").feature("rch4").set("kr", "kr4");
    model.component("comp2").physics("chem").feature("rch4").set("bulkRevOrder", "0");
    model.component("comp2").physics("chem").feature("rch4").set("surfRevOrder", "2");
    model.component("comp2").physics("chem").feature("rch4")
         .label("4: \u8868\u9762: O(ads) + H(ads) <=> OH(ads) + Rh(ads)");
    model.component("comp2").physics("chem").feature("rch4").set("rClass", "surface");
    model.component("comp2").physics("chem").create("rch5", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch5").set("rSequenceNo", "5");
    model.component("comp2").physics("chem").feature("rch5")
         .set("formula", "OH(ads) + H(ads) <=> H2O(ads) + Rh(ads)");
    model.component("comp2").physics("chem").feature("rch5").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch5").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch5").set("kf", "kf5");
    model.component("comp2").physics("chem").feature("rch5").set("bulkFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch5").set("surfFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch5").set("setKeq0", "0");
    model.component("comp2").physics("chem").feature("rch5").set("kr", "kr5");
    model.component("comp2").physics("chem").feature("rch5").set("bulkRevOrder", "0");
    model.component("comp2").physics("chem").feature("rch5").set("surfRevOrder", "2");
    model.component("comp2").physics("chem").feature("rch5")
         .label("5: \u8868\u9762: OH(ads) + H(ads) <=> H2O(ads) + Rh(ads)");
    model.component("comp2").physics("chem").feature("rch5").set("rClass", "surface");
    model.component("comp2").physics("chem").create("rch6", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch6").set("rSequenceNo", "6");
    model.component("comp2").physics("chem").feature("rch6").set("formula", "2 OH(ads) <=> O(ads) + H2O(ads)");
    model.component("comp2").physics("chem").feature("rch6").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch6").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch6").set("kf", "kf6");
    model.component("comp2").physics("chem").feature("rch6").set("bulkFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch6").set("surfFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch6").set("setKeq0", "0");
    model.component("comp2").physics("chem").feature("rch6").set("kr", "kr6");
    model.component("comp2").physics("chem").feature("rch6").set("bulkRevOrder", "0");
    model.component("comp2").physics("chem").feature("rch6").set("surfRevOrder", "2");
    model.component("comp2").physics("chem").feature("rch6")
         .label("6: \u8868\u9762: 2 OH(ads) <=> O(ads) + H2O(ads)");
    model.component("comp2").physics("chem").feature("rch6").set("rClass", "surface");
    model.component("comp2").physics("chem").feature("H2").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("H2").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("H2").set("specLabel", "H2");
    model.component("comp2").physics("chem").feature("H2").set("speciesNameInput", "H2");
    model.component("comp2").physics("chem").feature("H2").set("specName", "H2");
    model.component("comp2").physics("chem").feature("H2").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("H2").set("chemicalFormula", "H2");
    model.component("comp2").physics("chem").feature("H2").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("H2").set("M", "2.01596[g/mol]");
    model.component("comp2").physics("chem").feature("H2").set("z", "0");
    model.component("comp2").physics("chem").feature("H2").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("H2").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("H2").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("H2").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("H2").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("H2").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("H2").set("cLock", "0");
    model.component("comp2").physics("chem").feature("H2").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("H2").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("H2").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("H2").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("H2").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("Rh_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("Rh_surf").set("specLabel", "Rh(ads)");
    model.component("comp2").physics("chem").feature("Rh_surf").set("speciesNameInput", "Rh(ads)");
    model.component("comp2").physics("chem").feature("Rh_surf").set("specName", "Rh(ads)");
    model.component("comp2").physics("chem").feature("Rh_surf").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("Rh_surf").set("chemicalFormula", "Rh(ads)");
    model.component("comp2").physics("chem").feature("Rh_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("Rh_surf").set("M", "102.90549[g/mol]");
    model.component("comp2").physics("chem").feature("Rh_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("Rh_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("Rh_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("Rh_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("Rh_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("Rh_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("Rh_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("Rh_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("Rh_surf").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("Rh_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("Rh_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("Rh_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("Rh_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("Rh_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("Rh_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("H_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("H_surf").set("specLabel", "H(ads)");
    model.component("comp2").physics("chem").feature("H_surf").set("speciesNameInput", "H(ads)");
    model.component("comp2").physics("chem").feature("H_surf").set("specName", "H(ads)");
    model.component("comp2").physics("chem").feature("H_surf").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("H_surf").set("chemicalFormula", "H(ads)");
    model.component("comp2").physics("chem").feature("H_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("H_surf").set("M", "1.00798[g/mol]");
    model.component("comp2").physics("chem").feature("H_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("H_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("H_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("H_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("H_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("H_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("H_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("H_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("H_surf").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("H_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("H_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("H_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("H_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("H_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H_surf").set("SpeciesThermoaHi7", new String[]{"0"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").physics("chem").feature("O2").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("O2").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("O2").set("specLabel", "O2");
    model.component("comp2").physics("chem").feature("O2").set("speciesNameInput", "O2");
    model.component("comp2").physics("chem").feature("O2").set("specName", "O2");
    model.component("comp2").physics("chem").feature("O2").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("O2").set("chemicalFormula", "O2");
    model.component("comp2").physics("chem").feature("O2").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("O2").set("M", "31.9988[g/mol]");
    model.component("comp2").physics("chem").feature("O2").set("z", "0");
    model.component("comp2").physics("chem").feature("O2").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("O2").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("O2").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("O2").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("O2").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("O2").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("O2").set("cLock", "0");
    model.component("comp2").physics("chem").feature("O2").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("O2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("O2").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("O2").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("O2").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("O2").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("O2").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O2").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("O_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("O_surf").set("specLabel", "O(ads)");
    model.component("comp2").physics("chem").feature("O_surf").set("speciesNameInput", "O(ads)");
    model.component("comp2").physics("chem").feature("O_surf").set("specName", "O(ads)");
    model.component("comp2").physics("chem").feature("O_surf").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("O_surf").set("chemicalFormula", "O(ads)");
    model.component("comp2").physics("chem").feature("O_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("O_surf").set("M", "15.9994[g/mol]");
    model.component("comp2").physics("chem").feature("O_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("O_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("O_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("O_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("O_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("O_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("O_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("O_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("O_surf").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("O_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("O_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("O_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("O_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("O_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("O_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("H2O").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("H2O").set("specLabel", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("speciesNameInput", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("specName", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("H2O").set("chemicalFormula", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("H2O").set("M", "18.01536[g/mol]");
    model.component("comp2").physics("chem").feature("H2O").set("z", "0");
    model.component("comp2").physics("chem").feature("H2O").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("H2O").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("H2O").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("H2O").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("H2O").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("H2O").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("H2O").set("cLock", "0");
    model.component("comp2").physics("chem").feature("H2O").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("H2O").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("H2O").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("H2O").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("H2O").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("H2O").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("H2O_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("H2O_surf").set("specLabel", "H2O(ads)");
    model.component("comp2").physics("chem").feature("H2O_surf").set("speciesNameInput", "H2O(ads)");
    model.component("comp2").physics("chem").feature("H2O_surf").set("specName", "H2O(ads)");
    model.component("comp2").physics("chem").feature("H2O_surf").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("H2O_surf").set("chemicalFormula", "H2O(ads)");
    model.component("comp2").physics("chem").feature("H2O_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("H2O_surf").set("M", "18.01536[g/mol]");
    model.component("comp2").physics("chem").feature("H2O_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("H2O_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("H2O_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("H2O_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("H2O_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("H2O_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("H2O_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("H2O_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("H2O_surf").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("H2O_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("H2O_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("H2O_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("H2O_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("H2O_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("OH_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("OH_surf").set("specLabel", "OH(ads)");
    model.component("comp2").physics("chem").feature("OH_surf").set("speciesNameInput", "OH(ads)");
    model.component("comp2").physics("chem").feature("OH_surf").set("specName", "OH(ads)");
    model.component("comp2").physics("chem").feature("OH_surf").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("OH_surf").set("chemicalFormula", "OH(ads)");
    model.component("comp2").physics("chem").feature("OH_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("OH_surf").set("M", "17.00738[g/mol]");
    model.component("comp2").physics("chem").feature("OH_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("OH_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("OH_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("OH_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("OH_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("OH_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("OH_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("OH_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("OH_surf").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("OH_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("OH_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("OH_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("OH_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("OH_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("OH_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").create("N2", "SpeciesChem");
    model.component("comp2").physics("chem").feature("N2").set("SpeciesSource", "FreeSpecies");
    model.component("comp2").physics("chem").feature("N2").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("N2").set("specLabel", "N2");
    model.component("comp2").physics("chem").feature("N2").set("speciesNameInput", "N2");
    model.component("comp2").physics("chem").feature("N2").set("specName", "N2");
    model.component("comp2").physics("chem").feature("N2").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("N2").set("chemicalFormula", "N2");
    model.component("comp2").physics("chem").feature("N2").label("\u7269\u8d28: N2 (\u6eb6\u5242)");
    model.component("comp2").physics("chem").feature("N2").active(true);
    model.component("comp2").physics("chem").feature("N2").set("sType", "solvent");
    model.component("comp2").physics("chem").feature("N2").set("M", "28.0137[g/mol]");
    model.component("comp2").physics("chem").feature("N2").set("z", "0");
    model.component("comp2").physics("chem").feature("N2").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("N2").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("N2").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("N2").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("N2").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("N2").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("N2").set("cLock", "1");
    model.component("comp2").physics("chem").feature("N2").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("N2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("N2").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("N2").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("N2").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("N2").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("N2").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("N2").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").prop("simpropChem").set("rSequenceNo", "6");
    model.component("comp2").physics("chem").prop("simpropChem").set("sSequenceNo", "9");
    model.component("comp2").physics("chem").prop("mixture").set("hasPropertyPackage", "0");
    model.component("comp2").physics("chem").prop("mixture").set("PropertyPackage", "");
    model.component("comp2").physics("chem").prop("mixture").set("Thermodynamics", "0");
    model.component("comp2").physics("tds").field("concentration").component(new String[]{"cH2", "cH2O", "cO2"});
    model.component("comp2").physics("tds").feature("cat1").set("enableAdsorption", true);
    model.component("comp2").physics("tds").feature("cat1").setIndex("species", true, 0);
    model.component("comp2").physics("tds").feature("cat1").setIndex("species", true, 1);
    model.component("comp2").physics("tds").feature("cat1").setIndex("species", true, 2);
    model.component("comp2").physics("tds").feature("cat1").feature().create("srcc1", "SurfaceReactionCatalyst");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("DiffusionCoefficientSource", "chem");
    model.component("comp2").physics("tds").feature("cat1")
         .set("SurfaceSpecies", new String[]{"H", "H2O", "O", "OH", "Rh"});
    model.component("comp2").physics("tds").feature("cat1")
         .set("M", new String[]{"comp2.chem.M_H_surf", "comp2.chem.M_H2O_surf", "comp2.chem.M_O_surf", "comp2.chem.M_OH_surf", "comp2.chem.M_Rh_surf"});
    model.component("comp2").physics("tds").feature("cat1")
         .set("initialValues", new String[]{"1e-5[mol/m^2]", "1e-5[mol/m^2]", "1e-5[mol/m^2]", "1e-5[mol/m^2]", "1e-5[mol/m^2]"});
    model.component("comp2").physics("tds").feature("cat1").set("Sarea", "cat_area");
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .set("Rsurf_cH2_src", "root.comp2.chem.Rsurf_H2");
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .set("Rsurf_cH2O_src", "root.comp2.chem.Rsurf_H2O");
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .set("Rsurf_cO2_src", "root.comp2.chem.Rsurf_O2");
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .setIndex("Rssurf_src", "comp2.chem.R_H_surf", 0, 0);
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .setIndex("Rssurf_src", "comp2.chem.R_H2O_surf", 1, 0);
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .setIndex("Rssurf_src", "comp2.chem.R_O_surf", 2, 0);
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .setIndex("Rssurf_src", "comp2.chem.R_OH_surf", 3, 0);
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .setIndex("Rssurf_src", "comp2.chem.R_Rh_surf", 4, 0);
    model.component("comp2").physics("tds").feature("init1")
         .set("initc", new String[]{"c0H2_inflow", "0", "c0O2_inflow"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"cH2", "cH2O", "UserDefined", "cO2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684", "c0N2", "\u6c42\u89e3\u7684"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cH2", "cH2O", "c0N2", "cO2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesSource", "PorousCatalyst_cat1");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "tds.csurf_H", 0, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "tds.csurf_H2O", 1, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "tds.csurf_O", 2, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "tds.csurf_OH", 3, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "tds.csurf_Rh", 4, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("tds").feature("sp1")
         .set("z", new String[]{"root.comp2.chem.z_H2", "root.comp2.chem.z_H2O", "root.comp2.chem.z_O2"});
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("DiffusionCoefficientSource", "chem");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1").set("Dchem_cH2_src", "userdef");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1").set("Dchem_cH2O_src", "userdef");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1").set("Dchem_cO2_src", "userdef");
    model.component("comp2").physics().create("br", "PorousMediaFlowBrinkman", "geom1");
    model.component("comp2").physics("br").prop("ShapeProperty").set("order_fluid", "1");
    model.component("comp2").physics("br").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");

    model.component("comp2").multiphysics().create("rfd1", "ReactingFlowDS");
    model.component("comp2").multiphysics("rfd1").set("Fluid_physics", "br");
    model.component("comp2").multiphysics("rfd1").set("Species_physics", "tds");

    model.component("comp2").material().create("pmat1", "PorousMedia");
    model.component("comp2").material("pmat1").feature().create("fluid1", "Fluid", "comp2");
    model.component("comp2").material("pmat1").feature().create("solid1", "Solid", "comp2");
    model.component("comp2").material("pmat1").selection().set();

    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re").feature("sync1").set("chemTag", "chem");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "tds");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1").set("u_src", "root.comp2.u");
    model.component("comp1").physics("re").feature("sync1").set("momentumbalance", "br");
    model.component("comp2").physics("br").feature("porous1").feature("fluid1").set("mu_mat", "root.comp2.chem.eta");
    model.component("comp2").physics("br").feature("porous1").feature("fluid1")
         .set("rho_mat", "root.comp2.chem.rho");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "root.comp2.br.pA");

    model.study("std2").feature("time").setSolveFor("/physics/re", false);
    model.study("std2").feature("time").setSolveFor("/physics/chem", true);
    model.study("std2").feature("time").setSolveFor("/physics/tds", true);
    model.study("std2").feature("time").setSolveFor("/physics/br", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/rfd1", true);
    model.study("std1").feature("time").setSolveFor("/physics/chem", false);
    model.study("std1").feature("time").setSolveFor("/physics/tds", false);
    model.study("std1").feature("time").setSolveFor("/physics/br", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/rfd1", false);

    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2\uff0c\u50ac\u5316\u5242");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("do_reac", "12[mm]", "\u5916\u5f84\uff0c\u53cd\u5e94\u5668");
    model.param("par2").set("do_needle", "4[mm]", "\u5916\u5f84\uff0c\u9488");
    model.param("par2").set("wt_needle", "0.5[mm]", "\u58c1\u539a\uff0c\u9488");
    model.param("par2").set("wt_reac", "0.8[mm]", "\u58c1\u539a\uff0c\u53cd\u5e94\u5668");
    model.param("par2").set("v_inlet", "20[cm/s]", "\u5165\u53e3\u901f\u5ea6");
    model.param("par2").set("klH2_ads", "0.3[m^3/mol]", "Langmuir \u5e38\u6570\uff0cA");
    model.param("par2").set("cplH2_ads", "0.02[mol/kg]", "\u6700\u5927\u5438\u9644\uff0cA");
    model.param("par2").set("kfH2_ads", "2e-2[1/s]", "\u8d28\u91cf\u4f20\u9012\u5e38\u6570\uff0cA");
    model.param("par2").set("klO2_ads", "0.2[m^3/mol]", "Langmuir \u5e38\u6570\uff0cB");
    model.param("par2").set("cplO2_ads", "0.01[mol/kg]", "\u6700\u5927\u5438\u9644\uff0cB");
    model.param("par2").set("kfO2_ads", "2e-2[1/s]", "\u8d28\u91cf\u4f20\u9012\u5e38\u6570\uff0cB");
    model.param("par2").set("klH2O_ads", "0.1[m^3/mol]", "Langmuir \u5e38\u6570\uff0cC");
    model.param("par2").set("cplH2O_ads", "0.01[mol/kg]", "\u6700\u5927\u5438\u9644\uff0cC");
    model.param("par2").set("kfH2O_ads", "1e-2[1/s]", "\u8d28\u91cf\u4f20\u9012\u5e38\u6570\uff0cC");
    model.param("par2")
         .set("cH_surf_scale", "1E-6[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8868\u9762\u7269\u8d28");
    model.param("par2")
         .set("cH2O_surf_scale", "1e-12[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u5438\u6536\u7269\u8d28");
    model.param("par2")
         .set("cO_surf_scale", "1E-8[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8868\u9762\u7269\u8d28");
    model.param("par2")
         .set("cOH_surf_scale", "1E-11[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8868\u9762\u7269\u8d28");
    model.param("par2")
         .set("cRh_surf_scale", "2.7E-5[mol/m^2]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8868\u9762\u7269\u8d28");

    return model;
  }

  public static Model run3(Model model) {

    model.component("comp2").geom("geom1").lengthUnit("mm");
    model.component("comp2").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom1").feature("cyl1").set("r", "do_reac/2");
    model.component("comp2").geom("geom1").feature("cyl1").set("h", "5*do_reac");
    model.component("comp2").geom("geom1").feature("cyl1").set("axistype", "cartesian");
    model.component("comp2").geom("geom1").feature("cyl1").set("ax3", new int[]{1, 0, 0});
    model.component("comp2").geom("geom1").feature("cyl1").setIndex("layer", "wt_reac", 0);
    model.component("comp2").geom("geom1").run("cyl1");
    model.component("comp2").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp2").geom("geom1").feature("cyl2").set("r", "do_reac/2-wt_reac");
    model.component("comp2").geom("geom1").feature("cyl2").set("h", "0.7*do_reac");
    model.component("comp2").geom("geom1").feature("cyl2").set("pos", new String[]{"3*do_reac", "0", "0"});
    model.component("comp2").geom("geom1").feature("cyl2").set("axistype", "cartesian");
    model.component("comp2").geom("geom1").feature("cyl2").set("ax3", new int[]{1, 0, 0});
    model.component("comp2").geom("geom1").run("cyl2");
    model.component("comp2").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp2").geom("geom1").feature("cyl3").set("r", "do_needle/2");
    model.component("comp2").geom("geom1").feature("cyl3").set("h", "do_reac");
    model.component("comp2").geom("geom1").feature("cyl3").set("pos", new String[]{"2.7*do_reac/2", "0", "0"});
    model.component("comp2").geom("geom1").feature("cyl3").setIndex("layer", "wt_needle", 0);
    model.component("comp2").geom("geom1").run("cyl3");
    model.component("comp2").geom("geom1").create("dif1", "Difference");
    model.component("comp2").geom("geom1").feature("dif1").selection("input").set("cyl1");
    model.component("comp2").geom("geom1").feature("dif1").selection("input2").set("cyl3");
    model.component("comp2").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp2").geom("geom1").run("dif1");
    model.component("comp2").geom("geom1").create("uni1", "Union");
    model.component("comp2").geom("geom1").feature("uni1").selection("input").set("cyl2", "cyl3", "dif1");
    model.component("comp2").geom("geom1").run("uni1");
    model.component("comp2").geom("geom1").create("blk1", "Block");
    model.component("comp2").geom("geom1").feature("blk1").set("size", new String[]{"5*do_reac", "2*do_reac", "1"});
    model.component("comp2").geom("geom1").feature("blk1").setIndex("size", "3*do_reac", 2);
    model.component("comp2").geom("geom1").feature("blk1").set("pos", new String[]{"0", "-2*do_reac", "0"});
    model.component("comp2").geom("geom1").feature("blk1").setIndex("pos", "-1.5*do_reac", 2);
    model.component("comp2").geom("geom1").run("blk1");
    model.component("comp2").geom("geom1").create("dif2", "Difference");
    model.component("comp2").geom("geom1").feature("dif2").selection("input").set("uni1");
    model.component("comp2").geom("geom1").feature("dif2").selection("input2").set("blk1");
    model.component("comp2").geom("geom1").run("fin");
    model.component("comp2").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp2").geom("geom1").feature("mcf1").selection("input").set("fin", 19, 20);
    model.component("comp2").geom("geom1").run("mcf1");
    model.component("comp2").geom("geom1").create("igf1", "IgnoreFaces");
    model.component("comp2").geom("geom1").feature("igf1").selection("input").set("mcf1", 11, 20);
    model.component("comp2").geom("geom1").run("igf1");

    model.component("comp2").selection().create("sel1", "Explicit");
    model.component("comp2").selection("sel1").label("\u50ac\u5316\u5e8a");
    model.component("comp2").selection("sel1").set(4);
    model.component("comp2").selection().create("sel2", "Explicit");
    model.component("comp2").selection("sel2").label("\u5bf9\u79f0\u9762");
    model.component("comp2").selection("sel2").geom(2);
    model.component("comp2").selection("sel2").set(5, 19, 22);
    model.component("comp2").selection().create("sel3", "Explicit");
    model.component("comp2").selection("sel3").label("\u5165\u53e3\uff0c\u7269\u8d28 O2");
    model.component("comp2").selection("sel3").geom(2);
    model.component("comp2").selection("sel3").set(4);
    model.component("comp2").selection().create("sel4", "Explicit");
    model.component("comp2").selection("sel4").label("\u5165\u53e3\uff0c\u7269\u8d28 H2");
    model.component("comp2").selection("sel4").geom(2);
    model.component("comp2").selection("sel4").set(15);
    model.component("comp2").selection().create("sel5", "Explicit");
    model.component("comp2").selection("sel5").label("\u51fa\u53e3");
    model.component("comp2").selection("sel5").geom(2);
    model.component("comp2").selection("sel5").set(25);
    model.component("comp2").selection().create("cyl1", "Cylinder");
    model.component("comp2").selection("cyl1").label("\u7b2c\u4e00\u4e2a\u81ea\u7531\u591a\u5b54\u754c\u9762");
    model.component("comp2").selection("cyl1").set("entitydim", 2);
    model.component("comp2").selection("cyl1").set("top", "0.5*do_reac");
    model.component("comp2").selection("cyl1").set("bottom", 0);
    model.component("comp2").selection("cyl1").set("pos", new String[]{"2.5*do_reac", "0", "0"});
    model.component("comp2").selection("cyl1").setIndex("pos", "0.25*do_reac", 1);
    model.component("comp2").selection("cyl1").set("axistype", "x");
    model.component("comp2").selection().create("sel6", "Explicit");
    model.component("comp2").selection("sel6").label("\u81ea\u7531\u6d41\u52a8\u57df");
    model.component("comp2").selection("sel6").set(2, 5);
    model.component("comp2").selection().create("uni1", "Union");
    model.component("comp2").selection("uni1").label("\u81ea\u7531\u591a\u5b54\u4ecb\u8d28\u57df");
    model.component("comp2").selection("uni1").set("input", new String[]{"sel1", "sel6"});
    model.component("comp2").selection().create("adj1", "Adjacent");
    model.component("comp2").selection("adj1").label("\u56fa\u4f53\u57df");
    model.component("comp2").selection("adj1").set("input", new String[]{"uni1"});
    model.component("comp2").selection("adj1").set("outputdim", 3);
    model.component("comp2").selection().create("adj2", "Adjacent");
    model.component("comp2").selection("adj2").label("\u56fa\u4f53\u8fb9\u754c");
    model.component("comp2").selection("adj2").set("input", new String[]{"adj1"});
    model.component("comp2").selection().create("adj3", "Adjacent");
    model.component("comp2").selection("adj3").label("\u591a\u5b54\u5e8a\u8fb9\u754c");
    model.component("comp2").selection("adj3").set("input", new String[]{"sel1"});
    model.component("comp2").selection().create("dif1", "Difference");
    model.component("comp2").selection("dif1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp2").selection("dif1").set("entitydim", 2);
    model.component("comp2").selection("dif1").set("add", new String[]{"adj2"});
    model.component("comp2").selection("dif1").set("subtract", new String[]{"adj3"});
    model.component("comp2").selection().create("sel7", "Explicit");
    model.component("comp2").selection("sel7").label("\u5e8a\u8fb9\u754c");
    model.component("comp2").selection("sel7").geom(2);
    model.component("comp2").selection("sel7").set(18, 20, 21);

    model.component("comp2").material().create("mat1", "Common");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat1").label("Nitrogen");
    model.component("comp2").material("mat1").set("family", "air");
    model.component("comp2").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1200.0", "1.77230303E-6+6.27427545E-8*T^1-3.47278555E-11*T^2+1.01243201E-14*T^3"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1200.0", "1088.22121-0.365941919*T^1+7.88715035E-4*T^2-3.749223E-7*T^3+3.17599068E-11*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02801/R_const[K*mol/J]/T");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("dermethod", "manual");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("argders", new String[][]{{"pA", "d(pA*0.02801/R_const/T,pA)"}, {"T", "d(pA*0.02801/R_const/T,T)"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1200.0", "3.6969697E-4+9.74353924E-5*T^1-4.07587413E-8*T^2+7.68453768E-12*T^3"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1200.0", "1088.22121-0.365941919*T^1+7.88715035E-4*T^2-3.749223E-7*T^3+3.17599068E-11*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02801[kg/mol]");
    model.component("comp2").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat1").selection().all();
    model.component("comp2").material("mat1").propertyGroup("def").set("porosity", new String[]{"0.3"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1e-9"});
    model.component("comp2").material().move("mat1", 0);
    model.component("comp2").material("pmat1").selection().set(4);
    model.component("comp2").material("pmat1").feature("fluid1").set("link", "mat1");
    model.component("comp2").material("pmat1").feature("solid1").set("vfrac", "0.7");
    model.component("comp2").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1e-9"});

    model.component("comp2").physics("tds").selection().named("uni1");
    model.component("comp2").physics("tds").feature("cat1").set("enableAdsorption", false);
    model.component("comp2").physics("tds").feature("cat1").setIndex("initialValues", "c0H_surf", 0, 0);
    model.component("comp2").physics("tds").feature("cat1").setIndex("initialValues", "c0H2O_surf", 1, 0);
    model.component("comp2").physics("tds").feature("cat1").setIndex("initialValues", "c0O_surf", 2, 0);
    model.component("comp2").physics("tds").feature("cat1").setIndex("initialValues", "c0OH_surf", 3, 0);
    model.component("comp2").physics("tds").feature("cat1").setIndex("initialValues", "c0Rh_surf", 4, 0);
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("Dchem_cH2_src", "root.comp2.chem.DXX_H2");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("Dchem_cH2O_src", "root.comp2.chem.DXX_H2O");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("Dchem_cO2_src", "root.comp2.chem.DXX_O2");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("FluidDiffusivityModelType", "BruggemanModel");

    model.component("comp2").variable().create("var1");
    model.component("comp2").variable("var1")
         .label("\u53d8\u91cf 1\uff0c\u7531\u8868\u9762\u6d53\u5ea6\u5b9a\u4e49\u7684\u672c\u4f53\u6d53\u5ea6");
    model.component("comp2").variable("var1").selection().geom("geom1", 3);
    model.component("comp2").variable("var1").selection().set(4);

//    To import content from file, use:
//    model.component("comp2").variable("var1").loadFile("FILENAME");
    model.component("comp2").variable("var1").set("cs_Rh", "tds.csurf_Rh*cat_area");
    model.component("comp2").variable("var1").set("cs_H", "tds.csurf_H*cat_area");
    model.component("comp2").variable("var1").set("cs_O", "tds.csurf_O*cat_area");
    model.component("comp2").variable("var1").set("cs_OH", "tds.csurf_OH*cat_area");
    model.component("comp2").variable("var1").set("cads_cH2", "tds.cads_cH2*cat_area");
    model.component("comp2").variable("var1").set("cads_cO2", "tds.cads_cO2*cat_area");
    model.component("comp2").variable("var1").set("cads_cH2O", "tds.cads_cH2O*cat_area");

    model.component("comp2").physics("tds").feature("init1").setIndex("initc", 0, 0);
    model.component("comp2").physics("tds").feature("init1").setIndex("initc", 0, 2);
    model.component("comp2").physics("tds").create("cdm1", "Fluid", 3);
    model.component("comp2").physics("tds").feature("cdm1").selection().set(2, 5);
    model.component("comp2").physics("tds").feature("cdm1").set("DiffusionCoefficientSource", "chem");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cH2_src", "root.comp2.chem.DXX_H2");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cH2O_src", "root.comp2.chem.DXX_H2O");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cO2_src", "root.comp2.chem.DXX_O2");
    model.component("comp2").physics("tds").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("tds").feature("sym1").selection().named("sel2");
    model.component("comp2").physics("tds").create("in1", "Inflow", 2);
    model.component("comp2").physics("tds").feature("in1").selection().named("sel3");
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "c0O2_inflow", 2);
    model.component("comp2").physics("tds").create("in2", "Inflow", 2);
    model.component("comp2").physics("tds").feature("in2").selection().named("sel4");
    model.component("comp2").physics("tds").feature("in2").setIndex("c0", "c0H2_inflow", 0);
    model.component("comp2").physics("tds").create("out1", "Outflow", 2);
    model.component("comp2").physics("tds").feature("out1").selection().named("sel5");
    model.component("comp2").physics("br").selection().named("uni1");
    model.component("comp2").physics("br").prop("PhysicalModelProperty").set("StokesFlowProp", false);
    model.component("comp2").physics("br").create("fp1", "FluidProperties", 3);
    model.component("comp2").physics("br").feature("fp1").selection().set(2, 5);
    model.component("comp2").physics("br").create("inl1", "InletBoundary", 2);
    model.component("comp2").physics("br").feature("inl1").selection().named("sel3");
    model.component("comp2").physics("br").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp2").physics("br").feature("inl1").set("Uavfdf", "v_inlet");
    model.component("comp2").physics("br").create("inl2", "InletBoundary", 2);
    model.component("comp2").physics("br").feature("inl2").selection().named("sel4");
    model.component("comp2").physics("br").feature("inl2").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp2").physics("br").feature("inl2").set("Uavfdf", "v_inlet");
    model.component("comp2").physics("br").create("out1", "OutletBoundary", 2);
    model.component("comp2").physics("br").feature("out1").selection().named("sel5");
    model.component("comp2").physics("br").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("br").feature("sym1").selection().named("sel2");

    model.component("comp2").mesh("mesh1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("size1").selection().set(5);
    model.component("comp2").mesh("mesh1").feature("size1").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("size1").set("hauto", 7);
    model.component("comp2").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp2").mesh("mesh1").create("size2", "Size");
    model.component("comp2").mesh("mesh1").feature("size2").selection().geom("geom1", 2);
    model.component("comp2").mesh("mesh1").feature("size2").selection().set(6, 9, 10, 14, 20, 23);
    model.component("comp2").mesh("mesh1").feature("size2").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("size2").set("hauto", 4);
    model.component("comp2").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh1").feature("ftri1").selection().named("cyl1");
    model.component("comp2").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 4);
    model.component("comp2").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size2").selection().set(31, 32);
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size2").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", 0.5);
    model.component("comp2").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("swe1").selection().named("sel1");
    model.component("comp2").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp2").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 30);
    model.component("comp2").mesh("mesh1").create("cr1", "CornerRefinement");
    model.component("comp2").mesh("mesh1").feature("cr1").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("cr1").selection().set(2, 4, 5, 6);
    model.component("comp2").mesh("mesh1").feature("cr1").selection("boundary").set(6, 9, 10, 14, 20, 23);
    model.component("comp2").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size1").selection().set(26);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size1").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size2").selection().set(2, 6);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size2").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", 0.9);
    model.component("comp2").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp2").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("bl1").selection().named("sel6");
    model.component("comp2").mesh("mesh1").feature("bl1").set("sharpcorners", "trim");
    model.component("comp2").mesh("mesh1").feature("bl1").feature("blp").selection().set(6, 9, 10, 14, 18, 21, 23);
    model.component("comp2").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp2").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.75);
    model.component("comp2").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 4.5);
    model.component("comp2").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp2").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh1").feature("bl2").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("bl2").selection().named("sel1");
    model.component("comp2").mesh("mesh1").feature("bl2").set("sharpcorners", "trim");
    model.component("comp2").mesh("mesh1").feature("bl2").set("smoothtransition", false);
    model.component("comp2").mesh("mesh1").feature("bl2").feature("blp").selection().set(18, 20, 21);
    model.component("comp2").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 4);
    model.component("comp2").mesh("mesh1").feature("bl2").feature("blp").set("blstretch", 1.25);
    model.component("comp2").mesh("mesh1").feature("bl2").feature("blp").set("blhminfact", 2.75);
    model.component("comp2").mesh("mesh1").run();

    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/rfd1", false);
    model.study("std2").feature().move("stat", 0);
    model.study("std2").feature("time").set("tlist", "range(0,0.05,1)");
    model.study("std2").feature("time").setSolveFor("/physics/br", false);
    model.study("std2").showAutoSequences("all");
    model.study("std2").feature("time").set("usesol", true);

    model.sol("sol2").feature("v2").feature("comp2_cH2").set("scalemethod", "manual");
    model.sol("sol2").feature("v2").feature("comp2_cH2O").set("scalemethod", "manual");
    model.sol("sol2").feature("v2").feature("comp2_cO2").set("scalemethod", "manual");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_H").set("scalemethod", "manual");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_H").set("scaleval", "cH_surf_scale");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_H2O").set("scalemethod", "manual");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_H2O").set("scaleval", "cH2O_surf_scale");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_O").set("scalemethod", "manual");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_O").set("scaleval", "cO_surf_scale");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_OH").set("scalemethod", "manual");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_OH").set("scaleval", "cOH_surf_scale");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_Rh").set("scalemethod", "manual");
    model.sol("sol2").feature("v2").feature("comp2_tds_csurf_Rh").set("scaleval", "cRh_surf_scale");
    model.sol("sol2").feature("t1").set("atolglobalfactor", 0.05);
    model.sol("sol2").feature("t1").set("estrat", "exclude");
    model.sol("sol2").feature("t1").create("se1", "Segregated");
    model.sol("sol2").feature("t1").feature("se1").set("segstabacc", "segaacc");
    model.sol("sol2").feature("t1").feature("se1").set("segaaccdim", 5);
    model.sol("sol2").feature("t1").feature("se1").set("segaaccmix", 0.9);
    model.sol("sol2").feature("t1").feature("se1").set("segaaccdelay", 1);
    model.sol("sol2").feature("t1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol2").feature("t1").feature("se1").feature("ss1").label("\u6d53\u5ea6");
    model.sol("sol2").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp2_cH2", "comp2_cH2O", "comp2_cO2"});
    model.sol("sol2").feature("t1").feature("se1").feature("ss1").set("subdamp", "0.8");
    model.sol("sol2").feature("t1").feature("se1").feature("ss1").set("subjtech", "once");
    model.sol("sol2").feature("t1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol2").feature("t1").feature("se1").feature("ss2").label("\u8868\u9762\u6d53\u5ea6");
    model.sol("sol2").feature("t1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp2_tds_csurf_H", "comp2_tds_csurf_H2O", "comp2_tds_csurf_O", "comp2_tds_csurf_OH", "comp2_tds_csurf_Rh"});
    model.sol("sol2").feature("t1").feature("se1").feature("ss2").set("subdamp", "0.7");
    model.sol("sol2").feature("t1").feature("se1").feature("ss2").set("subjtech", "once");
    model.sol("sol2").feature("t1").feature("se1").feature().remove("ssDef");
    model.sol("sol2").feature("t1").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol2").feature("t1").feature("se1").feature("ll1")
         .set("lowerlimit", "comp2.tds.csurf_Rh 1e-16 comp2.tds.csurf_H 1e-16 comp2.tds.csurf_H2O 1e-16 comp2.tds.csurf_O 1e-16 comp2.tds.csurf_OH 1e-16");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").label("\u6d53\u5ea6, H2, \u6d41\u7ebf (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1")
         .set("expr", new String[]{"tds.tflux_cH2x", "tds.tflux_cH2y", "tds.tflux_cH2z"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col", "Color");
    model.result("pg3").feature("str1").feature("col").set("expr", "cH2");
    model.result("pg3").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg3").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg3").feature("str1").set("linetype", "ribbon");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").label("\u6d53\u5ea6, H2, \u8868\u9762 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cH2"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 21, 0);
    model.result("pg5").label("\u6d53\u5ea6, H2O, \u6d41\u7ebf (tds)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1")
         .set("expr", new String[]{"tds.tflux_cH2Ox", "tds.tflux_cH2Oy", "tds.tflux_cH2Oz"});

    return model;
  }

  public static Model run4(Model model) {
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg5").feature("str1").create("col", "Color");
    model.result("pg5").feature("str1").feature("col").set("expr", "cH2O");
    model.result("pg5").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg5").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg5").feature("str1").set("linetype", "ribbon");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 21, 0);
    model.result("pg6").label("\u6d53\u5ea6, H2O, \u8868\u9762 (tds)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cH2O"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 21, 0);
    model.result("pg7").label("\u6d53\u5ea6, O2, \u6d41\u7ebf (tds)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1")
         .set("expr", new String[]{"tds.tflux_cO2x", "tds.tflux_cO2y", "tds.tflux_cO2z"});
    model.result("pg7").feature("str1").set("posmethod", "start");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result("pg7").feature("str1").create("col", "Color");
    model.result("pg7").feature("str1").feature("col").set("expr", "cO2");
    model.result("pg7").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg7").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg7").feature("str1").set("linetype", "ribbon");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 21, 0);
    model.result("pg8").label("\u6d53\u5ea6, O2, \u8868\u9762 (tds)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"cO2"});
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u901f\u5ea6 (br)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("slc1", "Slice");
    model.result("pg9").feature("slc1").label("\u5207\u9762");
    model.result("pg9").feature("slc1").set("showsolutionparams", "on");
    model.result("pg9").feature("slc1").set("expr", "br.U");
    model.result("pg9").feature("slc1").set("colortable", "Rainbow");
    model.result("pg9").feature("slc1").set("smooth", "internal");
    model.result("pg9").feature("slc1").set("showsolutionparams", "on");
    model.result("pg9").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(6, 9, 10, 14, 20, 23);
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u538b\u529b (br)");
    model.result("pg10").set("data", "surf1");
    model.result("pg10").setIndex("looplevel", 21, 0);
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u8868\u9762");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("expr", "p");
    model.result("pg10").feature("surf1").set("colortable", "Dipole");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg10").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg3").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "dset3");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", -0.5);
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "3*do_reac", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "3.7*do_reac", 1, 0);
    model.result("pg9").run();
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").run();
    model.result("pg9").feature("slc1").set("quickxnumber", 8);
    model.result("pg9").feature("slc1").set("colortable", "Spectrum");
    model.result("pg9").run();
    model.result("pg9").label("\u901f\u5ea6\uff0c\u8868\u9762");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "br.U*1[cm]/br.nu");
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().named("sel2");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").create("tran1", "Transparency");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").feature("tran1").set("transparency", 0);
    model.result("pg9").run();
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("data", "cpl1");
    model.result("pg9").feature("arws1").setIndex("expr", "u", 0);
    model.result("pg9").feature("arws1").setIndex("expr", "v", 1);
    model.result("pg9").feature("arws1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg9").feature("arws1").set("arrowcount", 300);
    model.result("pg9").feature("arws1").set("color", "gray");
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg4").run();
    model.result("pg4").set("titletype", "auto");
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").label("\u8868\u9762\uff1a\u7ba1");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().named("dif1");
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").label("\u8868\u9762\uff1a\u50ac\u5316\u5242");
    model.result("pg4").feature("surf2").set("expr", "1");
    model.result("pg4").feature("surf2").set("titletype", "none");
    model.result("pg4").feature("surf2").set("coloring", "uniform");
    model.result("pg4").feature("surf2").set("color", "gray");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().named("sel7");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf2").feature("mtrl1").set("family", "wood");
    model.result("pg4").run();
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("selnumber", 10);
    model.result("pg4").feature("str1").selection().set(18);
    model.result("pg4").feature("str1").set("linetype", "ribbon");
    model.result("pg4").feature("str1").set("widthexpr", "br.U*1[s]");
    model.result("pg4").feature("str1").set("color", "white");
    model.result("pg4").run();
    model.result("pg4").create("iso1", "Isosurface");
    model.result("pg4").feature("iso1").label("cH2 \u6d53\u5ea6\u7b49\u503c\u9762");
    model.result("pg4").feature("iso1").set("levelmethod", "levels");
    model.result("pg4").feature("iso1").set("levels", "range(0.1,0.1,2)");
    model.result("pg4").feature("iso1").create("tran1", "Transparency");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg6").run();
    model.result("pg8").run();
    model.result("pg8").set("titletype", "auto");
    model.result("pg8").set("edges", false);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").label("\u8868\u9762\uff1a\u7ba1");
    model.result("pg8").feature("surf1").set("expr", "1");
    model.result("pg8").feature("surf1").set("titletype", "none");
    model.result("pg8").feature("surf1").set("coloring", "uniform");
    model.result("pg8").feature("surf1").set("color", "gray");
    model.result("pg8").feature("surf1").create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().named("dif1");
    model.result("pg8").run();
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2").label("\u8868\u9762\uff1a\u50ac\u5316\u5242");
    model.result("pg8").feature("surf2").set("expr", "1");
    model.result("pg8").feature("surf2").set("titletype", "none");
    model.result("pg8").feature("surf2").create("sel1", "Selection");
    model.result("pg8").feature("surf2").feature("sel1").selection().named("sel7");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg8").feature("surf2").feature("mtrl1").set("family", "wood");
    model.result("pg8").run();
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").setIndex("expr", "tds.tflux_cO2x", 0);
    model.result("pg8").feature("str1").setIndex("expr", "tds.tflux_cO2y", 1);
    model.result("pg8").feature("str1").setIndex("expr", "tds.tflux_cO2z", 2);
    model.result("pg8").feature("str1").set("selnumber", 10);
    model.result("pg8").feature("str1").selection().set(18);
    model.result("pg8").feature("str1").set("linetype", "ribbon");
    model.result("pg8").feature("str1").set("widthexpr", "br.U*1[s]");
    model.result("pg8").feature("str1").set("color", "white");
    model.result("pg8").run();
    model.result("pg8").create("iso1", "Isosurface");
    model.result("pg8").feature("iso1").set("expr", "cO2");
    model.result("pg8").feature("iso1").label("cO2 \u6d53\u5ea6\u7b49\u503c\u9762");
    model.result("pg8").feature("iso1").set("levelmethod", "levels");
    model.result("pg8").feature("iso1").set("levels", "range(0.5,0.025,0.9)");
    model.result("pg8").feature("iso1").set("colortable", "TrafficFlow");
    model.result("pg8").feature("iso1").create("tran1", "Transparency");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u591a\u5b54\u57df\uff1a\u672c\u4f53\u7269\u8d28");
    model.result("pg11").set("data", "cln1");
    model.result("pg11").setIndex("looplevelinput", "manual", 0);
    model.result("pg11").setIndex("looplevel", new int[]{21}, 0);
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr1").set("linewidth", "preference");
    model.result("pg11").feature("lngr1").set("titletype", "manual");
    model.result("pg11").feature("lngr1").set("title", "\u6469\u5c14\u6d53\u5ea6\uff0c\u672c\u4f53");
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").feature("lngr1").set("autosolution", false);
    model.result("pg11").feature("lngr1").set("autoexpr", true);
    model.result("pg11").run();
    model.result("pg11").create("lngr2", "LineGraph");
    model.result("pg11").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr2").set("linewidth", "preference");
    model.result("pg11").feature("lngr2").set("expr", "cO2");
    model.result("pg11").feature("lngr2").set("titletype", "none");
    model.result("pg11").feature("lngr2").set("legend", true);
    model.result("pg11").feature("lngr2").set("autosolution", false);
    model.result("pg11").feature("lngr2").set("autoexpr", true);
    model.result("pg11").run();
    model.result("pg11").create("lngr3", "LineGraph");
    model.result("pg11").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr3").set("linewidth", "preference");
    model.result("pg11").feature("lngr3").set("expr", "cH2O");
    model.result("pg11").feature("lngr3").set("titletype", "none");
    model.result("pg11").feature("lngr3").set("legend", true);
    model.result("pg11").feature("lngr3").set("autosolution", false);
    model.result("pg11").feature("lngr3").set("autoexpr", true);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("\u591a\u5b54\u57df\uff1a\u8868\u9762\u7269\u8d28");
    model.result("pg12").run();
    model.result("pg12").feature("lngr1").set("expr", "cs_Rh");
    model.result("pg12").feature("lngr1").set("title", "\u6469\u5c14\u6d53\u5ea6\uff0c\u8868\u9762");
    model.result("pg12").run();
    model.result("pg12").feature("lngr2").set("expr", "cs_H");
    model.result("pg12").run();
    model.result("pg12").feature("lngr3").set("expr", "cs_O");
    model.result("pg12").feature().duplicate("lngr4", "lngr3");
    model.result("pg12").run();
    model.result("pg12").feature("lngr4").set("expr", "cs_OH");
    model.result("pg12").run();
    model.result("pg11").run();
    model.result().duplicate("pg13", "pg11");
    model.result("pg13").run();
    model.result("pg13").label("\u591a\u5b54\u57df\uff1a\u6240\u6709\u7269\u8d28");
    model.result("pg13").run();
    model.result("pg13").feature("lngr1").label("\u672c\u4f53\uff1acH2");
    model.result("pg13").feature("lngr1").set("title", "\u6469\u5c14\u6d53\u5ea6");
    model.result("pg13").run();
    model.result("pg13").feature("lngr2").label("\u672c\u4f53\uff1acO2");
    model.result("pg13").run();
    model.result("pg13").feature("lngr3").label("\u672c\u4f53\uff1acH2O");
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("lngr4", "lngr2");
    model.result("pg13").feature().duplicate("lngr5", "lngr3");
    model.result("pg13").run();
    model.result("pg13").feature("lngr4").label("\u8868\u9762\uff1acs_Rh");
    model.result("pg13").feature("lngr4").set("expr", "cs_Rh");
    model.result("pg13").run();
    model.result("pg13").feature("lngr5").label("\u8868\u9762\uff1acs_H");
    model.result("pg13").feature("lngr5").set("expr", "cs_H");
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("lngr6", "lngr4");
    model.result("pg13").feature().duplicate("lngr7", "lngr5");
    model.result("pg13").run();
    model.result("pg13").feature("lngr6").label("\u8868\u9762\uff1acs_O");
    model.result("pg13").feature("lngr6").set("expr", "cs_O");
    model.result("pg13").run();
    model.result("pg13").feature("lngr7").label("\u8868\u9762\uff1acs_OH");
    model.result("pg13").feature("lngr7").set("expr", "cs_OH");
    model.result("pg13").feature().duplicate("lngr8", "lngr7");
    model.result("pg13").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg8").feature("surf1").feature("mtrl1").set("family", "custom");
    model.result("pg8").feature("surf1").feature("mtrl1").set("basis", true);
    model.result("pg8").feature("surf1").feature("mtrl1").setIndex("basisx", "0.0", 0);
    model.result("pg8").feature("surf1").feature("mtrl1").setIndex("basisx", 1, 1);
    model.result("pg8").feature("surf1").feature("mtrl1").setIndex("basisx", "0.0", 2);
    model.result("pg8").feature("surf1").feature("mtrl1").set("scale", 7);
    model.result("pg8").feature("surf1").feature("mtrl1").set("colornoise", true);
    model.result("pg8").feature("surf1").feature("mtrl1").set("colornoisescale", 1);
    model.result("pg8").feature("surf1").feature("mtrl1").set("colornoisefrequency", 2);
    model.result("pg8").feature("surf1").feature("mtrl1")
         .set("customnoisecolor", new double[]{0.7411764860153198, 0.7882353067398071, 0.8470588326454163});
    model.result("pg8").feature("surf1").feature("mtrl1").set("colornoisenormalscale", 3);
    model.result("pg8").feature("surf1").feature("mtrl1").set("roughness", 0.5);
    model.result("pg8").feature("surf1").feature("mtrl1").set("anisotropy", 0.05);
    model.result("pg8").feature("surf1").feature("mtrl1").set("metallic", 0.4);
    model.result("pg8").feature("surf1").feature("mtrl1").set("diffusewrap", 0.1);
    model.result("pg8").feature("surf1").feature("mtrl1")
         .set("customdiffuse", new double[]{0.7098039388656616, 0.7098039388656616, 0.7098039388656616});
    model.result("pg8").feature("surf1").feature("mtrl1")
         .set("customambient", new double[]{0.6196078658103943, 0.6196078658103943, 0.6196078658103943});
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().remove("pg5");
    model.result().remove("pg7");
    model.result("pg4").run();
    model.result("pg8").run();

    model.title("\u5e26\u6ce8\u5165\u7ba1\u7684\u591a\u5b54\u50ac\u5316\u53cd\u5e94\u5668");

    model
         .description("\u591a\u5b54\u50ac\u5316\u5242\u4e2d\u7684\u7a00\u7269\u8d28\u4f20\u9012 \u591a\u7269\u7406\u573a\u63a5\u53e3\u663e\u8457\u7b80\u5316\u4e86\u591a\u76f8\u50ac\u5316\u53cd\u5e94\u5668\u7684\u5efa\u6a21\uff0c\u5176\u4e2d\u5b9a\u4e49\u4e86\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8\u4e2d\u7684\u6269\u6563\u3001\u5bf9\u6d41\u3001\u8fc1\u79fb\u3001\u672c\u4f53\u53cd\u5e94\uff0c\u4ee5\u53ca\u591a\u5b54\u50ac\u5316\u5242\u4e2d\u5316\u5b66\u7269\u8d28\u7684\u5f02\u76f8\u53cd\u5e94\uff08\u5438\u9644\u3001\u89e3\u5438\u548c\u5176\u4ed6\u6c14-\u56fa\u8868\u9762\u53cd\u5e94\uff09\uff0c\u5e76\u81ea\u52a8\u5c06\u591a\u76f8\u50ac\u5316\u5efa\u6a21\u6240\u9700\u7684\u6240\u6709\u8026\u5408\u548c\u7269\u7406\u573a\u63a5\u53e3\u4e0e\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8\u548c\u7a00\u91ca\u5316\u5b66\u7269\u8d28\u4f20\u9012\u76f8\u7ed3\u5408\u3002\n\n\u591a\u76f8\u50ac\u5316\u53cd\u5e94\u5668\u5df2\u5e7f\u6cdb\u7528\u4e8e\u5316\u5de5\u884c\u4e1a\u3002\u8fd9\u79cd\u7c7b\u578b\u7684\u6c14-\u56fa\u50ac\u5316\u53cd\u5e94\u5668\uff08\u5176\u4e2d\u6c14\u76f8\u7269\u8d28\u7531\u56fa\u4f53\u50ac\u5316\u5242\u50ac\u5316\uff09\u5728\u50ac\u5316\u6c27\u5316\u3001\u9009\u62e9\u6027\u50ac\u5316\u8fd8\u539f\u7b49\u9886\u57df\u6709\u7740\u5e7f\u6cdb\u7684\u5e94\u7528\u3002\u672c\u4f8b\u6f14\u793a\u591a\u5b54\u50ac\u5316\u53cd\u5e94\u5668\u7684\u5efa\u6a21\uff0c\u5176\u4e2d\u7684\u5316\u5b66\u7cfb\u7edf\u662f\u6c22\u5728\u8d35\u91d1\u5c5e Rh \u50ac\u5316\u5242\u4e0a\u88ab\u6c27\u5316\uff0c\u7814\u7a76\u4e86\u591a\u76f8\u50ac\u5316\u53cd\u5e94\u7cfb\u7edf\u7684\u52a8\u529b\u5b66\u3001\u7269\u8d28\u6d53\u5ea6\u5206\u5e03\uff08\u5305\u62ec\u672c\u4f53\u6c14\u76f8\u7269\u8d28\u548c\u5438\u9644\u5728\u50ac\u5316\u8868\u9762\u4e0a\u7684\u7269\u8d28\uff09\u548c\u5b54\u9699\u4f53\u79ef\u4e2d\u7684\u901f\u5ea6\u573a\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("porous_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
