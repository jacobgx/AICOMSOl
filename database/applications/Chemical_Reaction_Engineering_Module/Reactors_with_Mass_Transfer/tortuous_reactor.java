/*
 * tortuous_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:25 by COMSOL 6.3.0.290. */
public class tortuous_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T_iso", "288[K]", "\u53cd\u5e94\u5668\u6e29\u5ea6 (K)");
    model.param().set("H", "5[um]", "\u901a\u9053\u9ad8\u5ea6\u6bd4\u4f8b\u53c2\u6570");
    model.param().set("c_RBr0", "50[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0cRBr");
    model.param().set("c_RH0", "0[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0cRH");
    model.param().set("c_RR0", "0[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0cRR");
    model.param().set("c_As0_ads", "1e-5[mol/m^2]", "\u5165\u53e3\u5904\u7684\u8868\u9762\u6d53\u5ea6\uff0cAs");
    model.param().set("A1", "2e-3[m/s]", "\u53cd\u5e94 1 \u7684\u9891\u7387\u56e0\u5b50");
    model.param().set("A2", "1e-1[m^4/(mol*s)]", "\u53cd\u5e94 2 \u7684\u9891\u7387\u56e0\u5b50");
    model.param().set("E1", "10e3[J/mol]", "\u53cd\u5e94 1 \u7684\u6d3b\u5316\u80fd");
    model.param().set("E2", "30e3[J/mol]", "\u53cd\u5e94 2 \u7684\u6d3b\u5316\u80fd");
    model.param().set("Mn_RBr", "0.151[kg/mol]", "RBr (C5H11Br) \u6469\u5c14\u8d28\u91cf");
    model.param().set("Mn_RH", "0.072[kg/mol]", "RH (C5H12) \u6469\u5c14\u8d28\u91cf");
    model.param().set("Mn_RR", "0.142[kg/mol]", "RR (C10H22) \u6469\u5c14\u8d28\u91cf");
    model.param().set("Mn_As", "0.195[kg/mol]", "\u50ac\u5316\u5242\u6469\u5c14\u8d28\u91cf");
    model.param().set("delta_p", "1.5[kPa]", "\u5165\u53e3\u8d85\u538b");
    model.param().set("D", "5e-7[m^2/s]*exp(-2000[K]/T_iso)", "\u6269\u6563\u7cfb\u6570");

    model.component("comp1").physics("re").prop("energybalance").set("T", "T_iso");
    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "RBr+AS(ads)=>RH");
    model.component("comp1").physics("re").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch1").set("r", "re.kf_1*re.c_RBr");
    model.component("comp1").physics("re").feature("rch1").set("surfFwdOrder", 0);
    model.component("comp1").physics("re").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch1").set("Af", "A1");
    model.component("comp1").physics("re").feature("rch1").set("Ef", "E1");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "2RBr+AS(ads)=>RR");
    model.component("comp1").physics("re").feature("rch2").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch2").set("surfFwdOrder", 0);
    model.component("comp1").physics("re").feature("rch2").set("r", "re.kf_2*re.c_RBr^2");
    model.component("comp1").physics("re").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch2").set("Af", "A2");
    model.component("comp1").physics("re").feature("rch2").set("Ef", "E2");
    model.component("comp1").physics("re").feature("AS_surf").set("cLock", true);
    model.component("comp1").physics("re").prop("reactor").set("surfaceAreaOptions", "surfaceAreaToVolumeRatio");
    model.component("comp1").physics("re").prop("reactor").set("as", "1/H");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_RBr0", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_RH0", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_RR0", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValues", "c_As0_ads", 0, 0);

    model.study("std1").feature("time").set("useparam", true);
    model.study("std1").feature("time").setIndex("pname", "T_iso", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "K", 0);
    model.study("std1").feature("time").setIndex("pname", "T_iso", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "K", 0);
    model.study("std1").feature("time").setIndex("plistarr", "288 343 363", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_RBr", "re.c_RH", "re.c_RR"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("\u6d53\u5ea6\u96f6\u7ef4\u6a21\u578b");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("data", "dset1");
    model.result("pg1").feature("glob1").set("titletype", "none");
    model.result("pg1").feature("glob1").setIndex("looplevelinput", "manual", 1);
    model.result("pg1").feature("glob1").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").run();
    model.result("pg1").feature("glob1").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg1").run();
    model.result("pg1").feature("glob1").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg1").run();

    model.component("comp1").physics("re").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re").feature("sync1").set("momentumbalance", "LaminarFlow");
    model.component("comp1").physics("re").prop("synchronize").set("synchronize", "1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 3);
    model.component("comp2").geom("geom1").geomRep("comsol");
    model.component("comp2").geom("geom1").axisymmetric(false);
    model.component("comp2").geom("geom1").label("\u51e0\u4f55 1(3D)");

    model.component("comp2").mesh().create("mesh1");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");

    model.component("comp1").physics("re").feature("sync1").set("genom", new String[]{"comp2:geom1"});
    model.component("comp1").physics("re").feature("sync1").set("studyname", new String[]{"comp2:std2"});
    model.component("comp2").physics().create("chem", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem", 0);
    model.component("comp2").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T", "T_iso");
    model.component("comp2").physics("chem").prop("mixture").set("mixture", "liquid");
    model.component("comp2").physics("chem").prop("mixture").set("liquidDensitySel", "Automatic");
    model.component("comp2").physics("chem").prop("Activity").set("useActivity", "0");
    model.component("comp2").physics("chem").prop("chemkin").set("chemkin", "0");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("VolumetricConcentrationGlobalActivityStandardState", "1[mol/m^3]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceSpeciesConcentrationType", "SurfaceConcentration");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceGlobalActivityStandardState", "1[mol/m^2]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SpeciesrateUserDefinedList", new String[]{"AS_surf"});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "4");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "3");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "1");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "1");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", "1");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventIsSet", "0");
    model.component("comp2").physics("chem").create("rch1", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch1").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").feature("rch1").set("formula", "RBr + AS(ads) => RH");
    model.component("comp2").physics("chem").feature("rch1").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch1").set("useArrhenius", "1");
    model.component("comp2").physics("chem").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch1").set("r", "chem.kf_1*chem.c_RBr");
    model.component("comp2").physics("chem").feature("rch1").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch1").set("Af", "A1");
    model.component("comp2").physics("chem").feature("rch1").set("nf", "0");
    model.component("comp2").physics("chem").feature("rch1").set("Ef", "E1");
    model.component("comp2").physics("chem").feature("rch1").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch1").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch1").label("1: \u8868\u9762: RBr + AS(ads) => RH");
    model.component("comp2").physics("chem").feature("rch1").set("rClass", "surface");
    model.component("comp2").physics("chem").create("rch2", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch2").set("rSequenceNo", "2");
    model.component("comp2").physics("chem").feature("rch2").set("formula", "2 RBr + AS(ads) => RR");
    model.component("comp2").physics("chem").feature("rch2").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch2").set("useArrhenius", "1");
    model.component("comp2").physics("chem").feature("rch2").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch2").set("r", "chem.kf_2*chem.c_RBr^2");
    model.component("comp2").physics("chem").feature("rch2").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch2").set("Af", "A2");
    model.component("comp2").physics("chem").feature("rch2").set("nf", "0");
    model.component("comp2").physics("chem").feature("rch2").set("Ef", "E2");
    model.component("comp2").physics("chem").feature("rch2").set("bulkFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch2").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch2").label("2: \u8868\u9762: 2 RBr + AS(ads) => RR");
    model.component("comp2").physics("chem").feature("rch2").set("rClass", "surface");
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("RBr").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("RBr").set("specLabel", "RBr");
    model.component("comp2").physics("chem").feature("RBr").set("speciesNameInput", "RBr");
    model.component("comp2").physics("chem").feature("RBr").set("specName", "RBr");
    model.component("comp2").physics("chem").feature("RBr").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("RBr").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("RBr").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("RBr").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("RBr").set("z", "0");
    model.component("comp2").physics("chem").feature("RBr").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("RBr").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("RBr").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("RBr").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("RBr").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("RBr").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("RBr").set("cLock", "0");
    model.component("comp2").physics("chem").feature("RBr").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("RBr").set("dependent", "0");
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("RBr").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("RBr").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("RBr").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("RBr").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RBr").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("AS_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("AS_surf").set("specLabel", "AS(ads)");
    model.component("comp2").physics("chem").feature("AS_surf").set("speciesNameInput", "AS(ads)");
    model.component("comp2").physics("chem").feature("AS_surf").set("specName", "AS(ads)");
    model.component("comp2").physics("chem").feature("AS_surf").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("AS_surf").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("AS_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("AS_surf").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("AS_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("AS_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("AS_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("AS_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("AS_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("AS_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("AS_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("AS_surf").set("cLock", "1");
    model.component("comp2").physics("chem").feature("AS_surf").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("AS_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("AS_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("AS_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("AS_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("AS_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("AS_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("RH").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("RH").set("specLabel", "RH");
    model.component("comp2").physics("chem").feature("RH").set("speciesNameInput", "RH");
    model.component("comp2").physics("chem").feature("RH").set("specName", "RH");
    model.component("comp2").physics("chem").feature("RH").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("RH").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("RH").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("RH").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("RH").set("z", "0");
    model.component("comp2").physics("chem").feature("RH").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("RH").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("RH").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("RH").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("RH").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("RH").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("RH").set("cLock", "0");
    model.component("comp2").physics("chem").feature("RH").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("RH").set("dependent", "0");
    model.component("comp2").physics("chem").feature("RH").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("RH").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("RH").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("RH").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("RH").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RH").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("RR").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("RR").set("specLabel", "RR");
    model.component("comp2").physics("chem").feature("RR").set("speciesNameInput", "RR");
    model.component("comp2").physics("chem").feature("RR").set("specName", "RR");
    model.component("comp2").physics("chem").feature("RR").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("RR").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("RR").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("RR").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("RR").set("z", "0");
    model.component("comp2").physics("chem").feature("RR").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("RR").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("RR").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("RR").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("RR").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("RR").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("RR").set("cLock", "0");
    model.component("comp2").physics("chem").feature("RR").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("RR").set("dependent", "0");
    model.component("comp2").physics("chem").feature("RR").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("RR").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("RR").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("RR").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("RR").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("RR").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").prop("simpropChem").set("rSequenceNo", "2");
    model.component("comp2").physics("chem").prop("simpropChem").set("sSequenceNo", "4");
    model.component("comp2").physics("chem").prop("mixture").set("hasPropertyPackage", "0");
    model.component("comp2").physics("chem").prop("mixture").set("PropertyPackage", "");
    model.component("comp2").physics("chem").prop("mixture").set("Thermodynamics", "0");
    model.component("comp2").physics("tds").field("concentration").component(new String[]{"cRBr", "cRH", "cRR"});
    model.component("comp2").physics("tds").feature("init1").set("initc", new String[]{"c_RBr0", "c_RH0", "c_RR0"});
    model.component("comp2").physics("tds").feature().create("reac1", "Reactions");
    model.component("comp2").physics("tds").feature("reac1").selection().all();
    model.component("comp2").physics("tds").feature("reac1").set("ReactingVolumeType", "TotalVolume");
    model.component("comp2").physics("tds").feature().create("srf1", "SurfaceReactionsFlux");
    model.component("comp2").physics("tds").feature().create("fd1", "FluxDiscontinuity");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"cRBr", "cRH", "cRR"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cRBr", "cRH", "cRR"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"c_As0_ads"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("tds").feature("sp1")
         .set("z", new String[]{"root.comp2.chem.z_RBr", "root.comp2.chem.z_RH", "root.comp2.chem.z_RR"});
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cRBr", "1e-9[m^2/s]");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cRBr", "1e-9[m^2/s]");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cRH", "1e-9[m^2/s]");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cRH", "1e-9[m^2/s]");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cRR", "1e-9[m^2/s]");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cRR", "1e-9[m^2/s]");
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cRBr_src", "root.comp2.chem.R_RBr", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cRH_src", "root.comp2.chem.R_RH", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cRR_src", "root.comp2.chem.R_RR", 0);
    model.component("comp2").physics("tds").feature("srf1").setIndex("J0_cRBr_src", "root.comp2.chem.Rsurf_RBr", 0);
    model.component("comp2").physics("tds").feature("srf1").setIndex("J0_cRH_src", "root.comp2.chem.Rsurf_RH", 0);
    model.component("comp2").physics("tds").feature("srf1").setIndex("J0_cRR_src", "root.comp2.chem.Rsurf_RR", 0);
    model.component("comp2").physics("tds").feature("fd1").setIndex("species", "1", 0);
    model.component("comp2").physics("tds").feature("fd1").setIndex("N0", "root.comp2.chem.Rsurf_RBr", 0);
    model.component("comp2").physics("tds").feature("fd1").setIndex("species", "1", 1);
    model.component("comp2").physics("tds").feature("fd1").setIndex("N0", "root.comp2.chem.Rsurf_RH", 1);
    model.component("comp2").physics("tds").feature("fd1").setIndex("species", "1", 2);
    model.component("comp2").physics("tds").feature("fd1").setIndex("N0", "root.comp2.chem.Rsurf_RR", 2);
    model.component("comp2").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp2").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");

    model.component("comp2").multiphysics().create("rfd1", "ReactingFlowDS", 3);
    model.component("comp2").multiphysics("rfd1").set("Fluid_physics", "spf");
    model.component("comp2").multiphysics("rfd1").set("Species_physics", "tds");

    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re").feature("sync1").set("chemTag", "chem");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "tds");
    model.component("comp2").physics("tds").feature("cdm1").set("minput_pressure_src", "root.comp2.spf.pA");
    model.component("comp2").physics("tds").feature("cdm1").set("u_src", "root.comp2.u");
    model.component("comp1").physics("re").feature("sync1").set("momentumbalance", "spf");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "root.comp2.spf.pA");

    model.study("std2").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/rfd1", true);
    model.study("std1").feature("time").setSolveFor("/physics/chem", false);
    model.study("std1").feature("time").setSolveFor("/physics/tds", false);
    model.study("std1").feature("time").setSolveFor("/physics/spf", false);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std1").feature("time").setSolveFor("/multiphysics/rfd1", false);

    model.component("comp2").geom("geom1").insertFile("tortuous_reactor_geom_sequence.mph", "geom1");
    model.component("comp2").geom("geom1").run("difsel1");

    model.component("comp2").material().create("mat1", "Common");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp2").material("mat1").label("Water");
    model.component("comp2").material("mat1").set("family", "water");
    model.component("comp2").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").label("Analytic ");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp2").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp2").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp2").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp2").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp2").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp2").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp2").physics("chem").feature("RBr").set("M", "Mn_RBr");
    model.component("comp2").physics("chem").feature("AS_surf").set("M", "Mn_As");
    model.component("comp2").physics("chem").feature("RH").set("M", "Mn_RH");
    model.component("comp2").physics("chem").feature("RR").set("M", "Mn_RR");
    model.component("comp2").physics("tds").feature("cdm1")
         .set("D_cRBr", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp2").physics("tds").feature("cdm1")
         .set("D_cRH", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp2").physics("tds").feature("cdm1")
         .set("D_cRR", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp2").physics("tds").feature("srf1").selection().named("geom1_boxsel1");
    model.component("comp2").physics("tds").create("conc1", "Concentration", 2);
    model.component("comp2").physics("tds").feature("conc1").selection().named("geom1_sel1");
    model.component("comp2").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp2").physics("tds").feature("conc1").setIndex("c0", "c_RBr0", 0);
    model.component("comp2").physics("tds").feature("conc1").setIndex("species", true, 1);
    model.component("comp2").physics("tds").feature("conc1").setIndex("c0", "c_RH0", 1);
    model.component("comp2").physics("tds").feature("conc1").setIndex("species", true, 2);
    model.component("comp2").physics("tds").feature("conc1").setIndex("c0", "c_RR0", 2);
    model.component("comp2").physics("tds").create("out1", "Outflow", 2);
    model.component("comp2").physics("tds").feature("out1").selection().named("geom1_sel2");
    model.component("comp2").physics("tds").feature().remove("reac1");
    model.component("comp2").physics("tds").feature().remove("fd1");
    model.component("comp2").physics("spf").feature("fp1").set("minput_temperature_src", "userdef");
    model.component("comp2").physics("spf").feature("fp1").set("minput_temperature", "T_iso");
    model.component("comp2").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp2").physics("spf").feature("inl1").selection().named("geom1_sel1");
    model.component("comp2").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp2").physics("spf").feature("inl1").set("p0", "delta_p");
    model.component("comp2").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp2").physics("spf").feature("out1").selection().named("geom1_sel2");

    model.component("comp2").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh1").feature("ftri1").selection().set(8);
    model.component("comp2").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 1);
    model.component("comp2").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("swe1").selection().named("geom1_csel1_dom");
    model.component("comp2").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 6);
    model.component("comp2").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("ftet1").selection().named("geom1_csel2_dom");
    model.component("comp2").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 2);
    model.component("comp2").mesh("mesh1").run();

    model.study("std2").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "T_iso", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "T_iso", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "288 343 363", 0);
    model.study("std2").create("stat2", "Stationary");
    model.study("std2").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std2").feature("stat2").set("useparam", true);
    model.study("std2").feature("stat2").setIndex("pname", "T_iso", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat2").setIndex("punit", "K", 0);
    model.study("std2").feature("stat2").setIndex("pname", "T_iso", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat2").setIndex("punit", "K", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "288 343 363", 0);
    model.study("std2").feature("stat2").set("usesol", true);
    model.study("std2").feature("stat2").set("notsolmethod", "sol");
    model.study("std2").feature("stat2").set("notstudy", "std2");
    model.study("std2").feature("stat2").set("notsolnum", "all");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").label("\u6d53\u5ea6, RBr, \u6d41\u7ebf (tds)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("typeintitle", true);
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1")
         .set("expr", new String[]{"tds.tflux_cRBrx", "tds.tflux_cRBry", "tds.tflux_cRBrz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col", "Color");
    model.result("pg2").feature("str1").feature("col").set("expr", "cRBr");
    model.result("pg2").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg2").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg2").feature("str1").set("linetype", "ribbon");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").label("\u6d53\u5ea6, RBr, \u8868\u9762 (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cRBr"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").label("\u6d53\u5ea6, RH, \u6d41\u7ebf (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1")
         .set("expr", new String[]{"tds.tflux_cRHx", "tds.tflux_cRHy", "tds.tflux_cRHz"});
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").feature("str1").create("col", "Color");
    model.result("pg4").feature("str1").feature("col").set("expr", "cRH");
    model.result("pg4").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg4").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg4").feature("str1").set("linetype", "ribbon");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").label("\u6d53\u5ea6, RH, \u8868\u9762 (tds)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"cRH"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").label("\u6d53\u5ea6, RR, \u6d41\u7ebf (tds)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1")
         .set("expr", new String[]{"tds.tflux_cRRx", "tds.tflux_cRRy", "tds.tflux_cRRz"});
    model.result("pg6").feature("str1").set("posmethod", "start");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result("pg6").feature("str1").create("col", "Color");
    model.result("pg6").feature("str1").feature("col").set("expr", "cRR");
    model.result("pg6").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg6").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg6").feature("str1").set("linetype", "ribbon");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 3, 0);
    model.result("pg7").label("\u6d53\u5ea6, RR, \u8868\u9762 (tds)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"cRR"});
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u901f\u5ea6 (spf)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("slc1", "Slice");
    model.result("pg8").feature("slc1").label("\u5207\u9762");
    model.result("pg8").feature("slc1").set("showsolutionparams", "on");
    model.result("pg8").feature("slc1").set("expr", "spf.U");
    model.result("pg8").feature("slc1").set("colortable", "Rainbow");
    model.result("pg8").feature("slc1").set("smooth", "internal");
    model.result("pg8").feature("slc1").set("showsolutionparams", "on");
    model.result("pg8").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 5, 7, 9, 10, 12, 13, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 26, 27, 28, 29, 30, 32, 33, 35, 36, 37, 38, 39, 41, 42, 44, 45, 46, 47, 48, 50, 51, 52, 53, 54, 56, 57, 58, 59, 60, 62, 63, 65, 66, 67, 68, 69, 71, 72, 74, 75, 76, 77, 78, 80, 81, 82, 83, 84, 86, 87, 88, 89, 90, 92, 93, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 106, 107, 110, 111, 112);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u538b\u529b (spf)");
    model.result("pg9").set("data", "surf1");
    model.result("pg9").setIndex("looplevel", 3, 0);
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("\u8868\u9762");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("expr", "p");
    model.result("pg9").feature("surf1").set("colortable", "Dipole");
    model.result("pg9").feature("surf1").set("smooth", "internal");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result("pg9").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortabletype", "discrete");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg8").run();
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").run();
    model.result("pg8").feature("slc1").set("titletype", "none");
    model.result("pg8").feature("slc1").set("quickplane", "xy");
    model.result("pg8").feature("slc1").set("quickznumber", 1);
    model.result("pg8").run();

    model.component("comp2").cpl().create("intop1", "Integration");
    model.component("comp2").cpl("intop1").set("axisym", true);
    model.component("comp2").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp2").cpl("intop1").selection().set(31);
    model.component("comp2").cpl().create("intop2", "Integration");
    model.component("comp2").cpl("intop2").set("axisym", true);
    model.component("comp2").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp2").cpl("intop2").selection().set(40);
    model.component("comp2").cpl().create("intop3", "Integration");
    model.component("comp2").cpl("intop3").set("axisym", true);
    model.component("comp2").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp2").cpl("intop3").selection().set(61);
    model.component("comp2").cpl().create("intop4", "Integration");
    model.component("comp2").cpl("intop4").set("axisym", true);
    model.component("comp2").cpl("intop4").selection().geom("geom1", 2);
    model.component("comp2").cpl("intop4").selection().set(70);
    model.component("comp2").cpl().create("intop5", "Integration");
    model.component("comp2").cpl("intop5").set("axisym", true);
    model.component("comp2").cpl("intop5").selection().geom("geom1", 2);
    model.component("comp2").cpl("intop5").selection().set(91);
    model.component("comp2").cpl().create("intop6", "Integration");
    model.component("comp2").cpl("intop6").set("axisym", true);
    model.component("comp2").cpl("intop6").selection().geom("geom1", 2);
    model.component("comp2").cpl("intop6").selection().set(85);

    model.sol("sol2").updateSolution();

    model.result("pg2").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("RBr \u6d53\u5ea6");
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("RH \u6d53\u5ea6");
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").label("RR \u6d53\u5ea6");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset2");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("expr", new String[]{"intop1(tds.ntflux_cRBr)/intop1(v*spf.ny)", "intop2(tds.ntflux_cRBr)/intop1(v*spf.ny)", "intop3(tds.ntflux_cRBr)/intop1(v*spf.ny)", "intop4(tds.ntflux_cRBr)/intop1(v*spf.ny)", "intop5(tds.ntflux_cRBr)/intop1(v*spf.ny)", "-intop6(tds.ntflux_cRBr)/intop1(v*spf.ny)"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u8fdb\u5165\u7b2c\u4e00\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RBr \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u4e8c\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RBr \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u4e09\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RBr \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u56db\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RBr \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u4e94\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RBr \u6d53\u5ea6", "\u6700\u540e\u4e00\u4e2a\u50ac\u5316\u6bb5\u540e\u8fb9\u754c\u4e0a\u7684 RBr \u6d53\u5ea6"});
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").set("data", "dset2");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg2").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("expr", new String[]{"intop1(tds.ntflux_cRH)/intop1(v*spf.ny)", "intop2(tds.ntflux_cRH)/intop1(v*spf.ny)", "intop3(tds.ntflux_cRH)/intop1(v*spf.ny)", "intop4(tds.ntflux_cRH)/intop1(v*spf.ny)", "intop5(tds.ntflux_cRH)/intop1(v*spf.ny)", "-intop6(tds.ntflux_cRH)/intop1(v*spf.ny)"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("descr", new String[]{"\u8fdb\u5165\u7b2c\u4e00\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RH \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u4e8c\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RH \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u4e09\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RH \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u56db\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RH \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u4e94\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RH \u6d53\u5ea6", "\u6700\u540e\u4e00\u4e2a\u50ac\u5316\u6bb5\u540e\u8fb9\u754c\u4e0a\u7684 RH \u6d53\u5ea6"});
    model.result().evaluationGroup("eg2").set("transpose", true);
    model.result().evaluationGroup("eg2").set("includeparameters", false);
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg3").feature("gev1").set("data", "dset2");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg3").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg3").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("expr", new String[]{"intop1(tds.ntflux_cRR)/intop1(v*spf.ny)", "intop2(tds.ntflux_cRR)/intop1(v*spf.ny)", "intop3(tds.ntflux_cRR)/intop1(v*spf.ny)", "intop4(tds.ntflux_cRR)/intop1(v*spf.ny)", "intop5(tds.ntflux_cRR)/intop1(v*spf.ny)", "-intop6(tds.ntflux_cRR)/intop1(v*spf.ny)"});
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("descr", new String[]{"\u8fdb\u5165\u7b2c\u4e00\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RR \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u4e8c\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RR \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u4e09\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RR \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u56db\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RR \u6d53\u5ea6", "\u8fdb\u5165\u7b2c\u4e94\u50ac\u5316\u6bb5\u65f6\u8fb9\u754c\u4e0a\u7684 RR \u6d53\u5ea6", "\u6700\u540e\u4e00\u4e2a\u50ac\u5316\u6bb5\u540e\u8fb9\u754c\u4e0a\u7684 RR \u6d53\u5ea6"});
    model.result().evaluationGroup("eg3").set("transpose", true);
    model.result().evaluationGroup("eg3").set("includeparameters", false);
    model.result().evaluationGroup("eg3").run();

    model.component("comp2").cpl().create("aveop1", "Average");
    model.component("comp2").cpl("aveop1").set("axisym", true);
    model.component("comp2").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp2").cpl("aveop1").selection().set(31);

    model.sol("sol2").updateSolution();

    model.result("pg2").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u6d53\u5ea6\uff0c\u96f6\u7ef4 vs. \u4e09\u7ef4");
    model.result("pg10").set("data", "none");
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u53cd\u5e94\u5668\u957f\u5ea6 (\\mu m)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").set("data", "dset1");
    model.result("pg10").feature("glob1").setIndex("looplevelinput", "last", 1);
    model.result("pg10").feature("glob1").setIndex("looplevelinput", "manualindices", 0);

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg10").feature("glob1").setIndex("looplevelindices", "range(1,1,90)", 0);
    model.result("pg10").feature("glob1").set("xdata", "expr");
    model.result("pg10").feature("glob1").set("xdataexpr", "t*withsol('sol3',comp2.aveop1(-comp2.v*comp2.spf.ny))");
    model.result("pg10").feature("glob1").set("linewidth", 2);
    model.result("pg10").feature("glob1").set("legendmethod", "manual");
    model.result("pg10").feature("glob1").setIndex("legends", "RBr", 0);
    model.result("pg10").feature("glob1").setIndex("legends", "RH", 1);
    model.result("pg10").feature("glob1").setIndex("legends", "RR", 2);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("tblp1", "Table");
    model.result("pg10").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg10").feature("tblp1").set("linewidth", "preference");
    model.result("pg10").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg10").feature("tblp1").set("xaxisdata", "rowindex");
    model.result("pg10").feature("tblp1").set("preprocx", "linear");
    model.result("pg10").feature("tblp1").set("scalingx", 80);
    model.result("pg10").feature("tblp1").set("shiftx", -80);
    model.result("pg10").feature("tblp1").set("linecolor", "blue");
    model.result("pg10").feature("tblp1").set("linemarker", "point");
    model.result("pg10").feature().duplicate("tblp2", "tblp1");
    model.result("pg10").run();
    model.result("pg10").feature("tblp2").set("evaluationgroup", "eg2");
    model.result("pg10").feature("tblp2").set("linecolor", "green");
    model.result("pg10").feature().duplicate("tblp3", "tblp2");
    model.result("pg10").run();
    model.result("pg10").feature("tblp3").set("evaluationgroup", "eg3");
    model.result("pg10").feature("tblp3").set("linecolor", "red");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg4");
    model.result().remove("pg6");
    model.result("pg3").run();

    model.title("\u86c7\u5f62\u5fae\u53cd\u5e94\u5668\u4e2d\u7684\u70c3\u8131\u5364");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u86c7\u5f62\u5fae\u53cd\u5e94\u5668\u4e2d\u53d1\u751f\u7684\u70c3\u8131\u5364\u4f5c\u7528\u3002\u53cd\u5e94\u7269\u4ece\u672c\u4f53\u4f20\u9012\u5230\u53cd\u5e94\u5668\u58c1\u4e0a\u7684\u50ac\u5316\u8868\u9762\uff0c\u53d1\u751f\u53cd\u5e94\u3002\u9996\u5148\u5efa\u7acb\u4e00\u4e2a\u968f\u65f6\u95f4\u53d8\u5316\u7684\u6a21\u578b\uff0c\u4f7f\u7528\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u5206\u6790\u4e24\u4e2a\u7ade\u4e89\u53cd\u5e94\uff0c\u518d\u5efa\u7acb\u4e00\u4e2a\u968f\u7a7a\u95f4\u53d8\u5316\u7684\u6a21\u578b\uff0c\u6a21\u62df\u6d41\u4f53\u6d41\u52a8\u548c\u8d28\u91cf\u4f20\u9012\uff0c\u7136\u540e\u6c42\u89e3\u8fd9\u4e24\u4e2a\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("tortuous_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
