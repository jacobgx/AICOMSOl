/*
 * drug_release.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:24 by COMSOL 6.3.0.290. */
public class drug_release {

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
    model.param().set("T_in", "300[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param()
         .set("kf_mm", "7.336e-3[s^-1]", "\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0c\u57fa\u8d28\u964d\u89e3\u9075\u5faa\u7c73\u6c0f\u52a8\u529b\u5b66");
    model.param().set("Km", "0.01[mol/m^3]", "\u7c73\u6c0f\u5e38\u6570");
    model.param()
         .set("kf_d", "1.5e4[m^3/(s*mol)]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0c\u836f\u7269\u5206\u79bb");
    model.param()
         .set("kr_d", "13.5[1/s]", "\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0c\u836f\u7269\u5206\u79bb");
    model.param()
         .set("ce_init", "2e-3[mol/m^3]", "\u9176\u7684\u521d\u59cb\u6d53\u5ea6\uff0c\u751f\u7269\u6750\u6599\u57fa\u8d28");
    model.param()
         .set("cnE_init", "2e-3[mol/m^3]", "\u9176\u7684\u521d\u59cb\u6d53\u5ea6\uff0c\u795e\u7ecf\u7ec6\u80de\u7ec4\u7ec7");
    model.param()
         .set("cmp_init", "1e-6[mol/m^2]", "\u57fa\u8d28\u7ed3\u5408\u80bd\u7684\u521d\u59cb\u6d53\u5ea6\uff0c\u751f\u7269\u6750\u6599\u57fa\u8d28");
    model.param()
         .set("cmpd_init", "1e-6[mol/m^2]", "\u57fa\u8d28\u7ed3\u5408\u836f\u7269-\u80bd\u7269\u8d28\u7684\u521d\u59cb\u6d53\u5ea6\uff0c\u751f\u7269\u6750\u6599\u57fa\u8d28");
    model.param().set("c_solv", "rhoh2o/Mnh2o", "\u6d53\u5ea6\uff0c\u6eb6\u5242\uff08\u6c34\uff09");
    model.param()
         .set("Dd", "8.93e-11[m^2/s]", "\u57fa\u8d28\u7ed3\u5408\u836f\u7269\u7684\u6269\u6563\u7cfb\u6570\uff0c\u751f\u7269\u6750\u6599\u57fa\u8d28");
    model.param()
         .set("Dp", "1.58e-10[m^2/s]", "\u836f\u7269\u7684\u6269\u6563\u7cfb\u6570\uff0c\u751f\u7269\u6750\u6599\u57fa\u8d28");
    model.param()
         .set("Dpd", "8.3e-11[m^2/s]", "\u80bd\u7684\u6269\u6563\u7cfb\u6570\uff0c\u751f\u7269\u6750\u6599\u57fa\u8d28");
    model.param()
         .set("De", "5e-11[m^2/s]", "\u9176\u7684\u6269\u6563\u7cfb\u6570\uff0c\u751f\u7269\u6750\u6599\u57fa\u8d28");
    model.param()
         .set("Dd_s", "8.3e-11[m^2/s]", "\u836f\u7269\u7684\u6269\u6563\u7cfb\u6570\uff0c\u5468\u56f4\u73af\u5883");
    model.param()
         .set("Dp_s", "1.68e-10[m^2/s]", "\u80bd\u7684\u6269\u6563\u7cfb\u6570\uff0c\u5468\u56f4\u73af\u5883");
    model.param()
         .set("Dpd_s", "9.31e-11[m^2/s]", "\u836f\u7269-\u80bd\u7269\u8d28\u7684\u6269\u6563\u7cfb\u6570\uff0c\u5468\u56f4\u73af\u5883");
    model.param()
         .set("De_s", "5.5e-11[m^2/s]", "\u9176\u7684\u6269\u6563\u7cfb\u6570\uff0c\u5468\u56f4\u73af\u5883");
    model.param()
         .set("Dd_n", "8.93e-11[m^2/s]", "\u836f\u7269\u7684\u6269\u6563\u7cfb\u6570\uff0c\u795e\u7ecf\u7ec6\u80de\u7ec4\u7ec7");
    model.param()
         .set("Dp_n", "1.58e-10[m^2/s]", "\u80bd\u7684\u6269\u6563\u7cfb\u6570\uff0c\u795e\u7ecf\u7ec6\u80de\u7ec4\u7ec7");
    model.param()
         .set("Dpd_n", "8.3e-11[m^2/s]", "\u836f\u7269-\u80bd\u7269\u8d28\u7684\u6269\u6563\u7cfb\u6570\uff0c\u795e\u7ecf\u7ec6\u80de\u7ec4\u7ec7");
    model.param()
         .set("De_n", "5e-11[m^2/s]", "\u9176\u7684\u6269\u6563\u7cfb\u6570\uff0c\u795e\u7ecf\u7ec6\u80de\u7ec4\u7ec7");
    model.param().set("Mnd", "10[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u836f\u7269");
    model.param().set("Mnp", "1[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u80bd");
    model.param().set("Mnpd", "11[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u836f\u7269-\u80bd\u7269\u8d28");
    model.param().set("Mne", "50[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u9176");
    model.param().set("Mnh2o", "0.018[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6eb6\u5242\uff08\u6c34\uff09");
    model.param().set("rhoh2o", "1000[kg/m^3]", "\u6eb6\u5242\uff08\u6c34\uff09\u5bc6\u5ea6");
    model.param().set("Ssa", "1e4[m^2/m^3]", "\u751f\u7269\u6750\u6599\u7684\u6bd4\u8868\u9762\u79ef");
    model.param().set("epsBio", "0.65", "\u751f\u7269\u6750\u6599\u7684\u5b54\u9699\u7387");

    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "d+mp(ads)=>mpd(ads)");
    model.component("comp1").physics("re").feature("rch1").set("rType", "rev");
    model.component("comp1").physics("re").feature("rch1").set("kf", "kf_d");
    model.component("comp1").physics("re").feature("rch1").set("kr", "kr_d");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "d+p<=>pd");
    model.component("comp1").physics("re").feature("rch2").set("kf", "kf_d");
    model.component("comp1").physics("re").feature("rch2").set("kr", "kr_d");
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "mp(ads)+e=>p+e");
    model.component("comp1").physics("re").feature("rch3").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch3")
         .set("r", "kf_mm*re.c_e*re.csurf_mp_surf/(Km+re.csurf_mp_surf*Ssa)");
    model.component("comp1").physics("re").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch4").set("formula", "mpd(ads)+e=>pd+e");
    model.component("comp1").physics("re").feature("rch4").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch4")
         .set("r", "kf_mm*re.c_e*re.csurf_mpd_surf/(Km+re.csurf_mpd_surf*Ssa)");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "h2o");
    model.component("comp1").physics("re").feature("h2o").set("sType", "solvent");
    model.component("comp1").physics("re").prop("reactor").set("surfaceAreaOptions", "surfaceAreaToVolumeRatio");
    model.component("comp1").physics("re").prop("reactor").set("as", "Ssa");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "ce_init", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_solv", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValues", "cmp_init", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValues", "cmpd_init", 1, 0);
    model.component("comp1").physics("re").feature("inits1").set("gamman0", "(cmp_init+cmpd_init)");

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,16)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_d", "re.csurf_mp_surf", "re.csurf_mpd_surf", "re.c_p", "re.c_pd", "re.c_e"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("\u751f\u7269\u6750\u6599\u6d53\u5ea6\uff0c\u96f6\u7ef4\u6a21\u578b");
    model.result("pg1").set("xlog", true);
    model.result("pg1").run();
    model.result("pg1").feature("glob1").setIndex("expr", "re.csurf_mp_surf*Ssa", 1);
    model.result("pg1").feature("glob1").setIndex("expr", "re.csurf_mpd_surf*Ssa", 2);
    model.result("pg1").feature("glob1").set("xdataparamunit", "s");
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u836f\u7269", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "\u57fa\u8d28\u7ed3\u5408\u80bd", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "\u57fa\u8d28\u7ed3\u5408\u836f\u7269\u80bd", 2);
    model.result("pg1").feature("glob1").setIndex("legends", "\u80bd", 3);
    model.result("pg1").feature("glob1").setIndex("legends", "\u836f\u7269-\u80bd", 4);
    model.result("pg1").feature("glob1").setIndex("legends", "\u9176", 5);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg1").set("legendpos", "middleleft");

    model.component("comp1").physics("re").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "2Daxi");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "ReactingFlowPorousMedia");
    model.component("comp1").physics("re").feature("sync1").set("MultipleMassTransfer", "PorousCatalyst");
    model.component("comp1").physics("re").feature("sync1").set("study", "Transient");
    model.component("comp1").physics("re").prop("synchronize").set("synchronize", "1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 2);
    model.component("comp2").geom("geom1").axisymmetric(true);
    model.component("comp2").geom("geom1").label("\u51e0\u4f55 1(2Daxi)");

    model.component("comp2").mesh().create("mesh1");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");

    model.component("comp1").physics("re").feature("sync1").set("genom", new String[]{"comp2:geom1"});
    model.component("comp1").physics("re").feature("sync1").set("studyname", new String[]{"comp2:std2"});
    model.component("comp2").physics().create("chem", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem", 0);
    model.component("comp2").physics().create("tds", "DilutedSpeciesInPorousCatalysts", "geom1");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T", "293.15[K]");
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
         .set("SpeciesrateUserDefinedList", new String[]{"h2o"});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "7");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "5");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "2");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "2");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", "1");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventIsSet", "1");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventTag", "h2o");
    model.component("comp2").physics("chem").prop("calcTransport").set("etaRef", "1.787E-3[Pa*s]");
    model.component("comp2").physics("chem").prop("calcTransport").set("Tref", "273[K]");
    model.component("comp2").physics("chem").create("rch1", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch1").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").feature("rch1").set("formula", "d + mp(ads) <=> mpd(ads)");
    model.component("comp2").physics("chem").feature("rch1").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch1").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch1").set("kf", "kf_d");
    model.component("comp2").physics("chem").feature("rch1").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch1").set("surfFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch1").set("setKeq0", "0");
    model.component("comp2").physics("chem").feature("rch1").set("kr", "kr_d");
    model.component("comp2").physics("chem").feature("rch1").set("bulkRevOrder", "0");
    model.component("comp2").physics("chem").feature("rch1").set("surfRevOrder", "1");
    model.component("comp2").physics("chem").feature("rch1").label("1: \u8868\u9762: d + mp(ads) <=> mpd(ads)");
    model.component("comp2").physics("chem").feature("rch1").set("rClass", "surface");
    model.component("comp2").physics("chem").create("rch2", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch2").set("rSequenceNo", "2");
    model.component("comp2").physics("chem").feature("rch2").set("formula", "d + p <=> pd");
    model.component("comp2").physics("chem").feature("rch2").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch2").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch2").set("kf", "kf_d");
    model.component("comp2").physics("chem").feature("rch2").set("bulkFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch2").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch2").set("setKeq0", "0");
    model.component("comp2").physics("chem").feature("rch2").set("kr", "kr_d");
    model.component("comp2").physics("chem").feature("rch2").set("bulkRevOrder", "1");
    model.component("comp2").physics("chem").feature("rch2").set("surfRevOrder", "0");
    model.component("comp2").physics("chem").feature("rch2").label("2: d + p <=> pd");
    model.component("comp2").physics("chem").feature("rch2").set("rClass", "volumetric");
    model.component("comp2").physics("chem").create("rch3", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch3").set("rSequenceNo", "3");
    model.component("comp2").physics("chem").feature("rch3").set("formula", "mp(ads) + e => p + e");
    model.component("comp2").physics("chem").feature("rch3").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch3").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch3").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch3")
         .set("r", "kf_mm*chem.c_e*chem.csurf_mp_surf/(Km+chem.csurf_mp_surf*Ssa)");
    model.component("comp2").physics("chem").feature("rch3").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch3").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch3").set("surfFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch3").label("3: \u8868\u9762: mp(ads) + e => p + e");
    model.component("comp2").physics("chem").feature("rch3").set("rClass", "surface");
    model.component("comp2").physics("chem").create("rch4", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch4").set("rSequenceNo", "4");
    model.component("comp2").physics("chem").feature("rch4").set("formula", "mpd(ads) + e => pd + e");
    model.component("comp2").physics("chem").feature("rch4").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch4").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch4").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch4")
         .set("r", "kf_mm*chem.c_e*chem.csurf_mpd_surf/(Km+chem.csurf_mpd_surf*Ssa)");
    model.component("comp2").physics("chem").feature("rch4").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch4").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch4").set("surfFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch4").label("4: \u8868\u9762: mpd(ads) + e => pd + e");
    model.component("comp2").physics("chem").feature("rch4").set("rClass", "surface");
    model.component("comp2").physics("chem").feature("d").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("d").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("d").set("specLabel", "d");
    model.component("comp2").physics("chem").feature("d").set("speciesNameInput", "d");
    model.component("comp2").physics("chem").feature("d").set("specName", "d");
    model.component("comp2").physics("chem").feature("d").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("d").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("d").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("d").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("d").set("z", "0");
    model.component("comp2").physics("chem").feature("d").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("d").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("d").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("d").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("d").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("d").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("d").set("cLock", "0");
    model.component("comp2").physics("chem").feature("d").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("d").set("dependent", "0");
    model.component("comp2").physics("chem").feature("d").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("d").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("d").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("d").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("d").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("d").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("mp_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("mp_surf").set("specLabel", "mp(ads)");
    model.component("comp2").physics("chem").feature("mp_surf").set("speciesNameInput", "mp(ads)");
    model.component("comp2").physics("chem").feature("mp_surf").set("specName", "mp(ads)");
    model.component("comp2").physics("chem").feature("mp_surf").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("mp_surf").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("mp_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("mp_surf").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("mp_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("mp_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("mp_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("mp_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("mp_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("mp_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("mp_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("mp_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("mp_surf").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("mp_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("mp_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("mp_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("mp_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("mp_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mp_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("mpd_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("mpd_surf").set("specLabel", "mpd(ads)");
    model.component("comp2").physics("chem").feature("mpd_surf").set("speciesNameInput", "mpd(ads)");
    model.component("comp2").physics("chem").feature("mpd_surf").set("specName", "mpd(ads)");
    model.component("comp2").physics("chem").feature("mpd_surf").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("mpd_surf").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("mpd_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("mpd_surf").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("mpd_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("mpd_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("mpd_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("mpd_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("mpd_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("mpd_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("mpd_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("mpd_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("mpd_surf").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("mpd_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("mpd_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("mpd_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("mpd_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("mpd_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("mpd_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("p").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("p").set("specLabel", "p");
    model.component("comp2").physics("chem").feature("p").set("speciesNameInput", "p");
    model.component("comp2").physics("chem").feature("p").set("specName", "p");
    model.component("comp2").physics("chem").feature("p").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("p").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("p").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("p").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("p").set("z", "0");
    model.component("comp2").physics("chem").feature("p").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("p").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("p").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("p").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("p").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("p").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("p").set("cLock", "0");
    model.component("comp2").physics("chem").feature("p").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("p").set("dependent", "0");
    model.component("comp2").physics("chem").feature("p").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("p").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("p").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("p").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("p").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi7", new String[]{"0"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").physics("chem").feature("pd").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("pd").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("pd").set("specLabel", "pd");
    model.component("comp2").physics("chem").feature("pd").set("speciesNameInput", "pd");
    model.component("comp2").physics("chem").feature("pd").set("specName", "pd");
    model.component("comp2").physics("chem").feature("pd").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("pd").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("pd").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("pd").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("pd").set("z", "0");
    model.component("comp2").physics("chem").feature("pd").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("pd").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("pd").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("pd").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("pd").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("pd").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("pd").set("cLock", "0");
    model.component("comp2").physics("chem").feature("pd").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("pd").set("dependent", "0");
    model.component("comp2").physics("chem").feature("pd").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("pd").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("pd").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("pd").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("pd").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("pd").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("e").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("e").set("specLabel", "e");
    model.component("comp2").physics("chem").feature("e").set("speciesNameInput", "e");
    model.component("comp2").physics("chem").feature("e").set("specName", "e");
    model.component("comp2").physics("chem").feature("e").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("e").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("e").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("e").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("e").set("z", "0");
    model.component("comp2").physics("chem").feature("e").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("e").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("e").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("e").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("e").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("e").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("e").set("cLock", "0");
    model.component("comp2").physics("chem").feature("e").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("e").set("dependent", "0");
    model.component("comp2").physics("chem").feature("e").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("e").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("e").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("e").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("e").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").create("h2o", "SpeciesChem");
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesSource", "FreeSpecies");
    model.component("comp2").physics("chem").feature("h2o").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("h2o").set("specLabel", "h2o");
    model.component("comp2").physics("chem").feature("h2o").set("speciesNameInput", "h2o");
    model.component("comp2").physics("chem").feature("h2o").set("specName", "h2o");
    model.component("comp2").physics("chem").feature("h2o").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("h2o").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("h2o").label("\u7269\u8d28: h2o (\u6eb6\u5242)");
    model.component("comp2").physics("chem").feature("h2o").active(true);
    model.component("comp2").physics("chem").feature("h2o").set("sType", "solvent");
    model.component("comp2").physics("chem").feature("h2o").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("h2o").set("z", "0");
    model.component("comp2").physics("chem").feature("h2o").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("h2o").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("h2o").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("h2o").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("h2o").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("h2o").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("h2o").set("cLock", "1");
    model.component("comp2").physics("chem").feature("h2o").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("h2o").set("dependent", "0");
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("h2o").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("h2o").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("h2o").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("h2o").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("h2o").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").prop("simpropChem").set("rSequenceNo", "4");
    model.component("comp2").physics("chem").prop("simpropChem").set("sSequenceNo", "7");
    model.component("comp2").physics("chem").prop("mixture").set("hasPropertyPackage", "0");
    model.component("comp2").physics("chem").prop("mixture").set("PropertyPackage", "");
    model.component("comp2").physics("chem").prop("mixture").set("Thermodynamics", "0");
    model.component("comp2").physics("tds").field("concentration").component(new String[]{"cd", "ce", "cp", "cpd"});
    model.component("comp2").physics("tds").feature("cat1").set("enableAdsorption", true);
    model.component("comp2").physics("tds").feature("cat1").setIndex("species", true, 0);
    model.component("comp2").physics("tds").feature("cat1").setIndex("species", true, 1);
    model.component("comp2").physics("tds").feature("cat1").setIndex("species", true, 2);
    model.component("comp2").physics("tds").feature("cat1").setIndex("species", true, 3);
    model.component("comp2").physics("tds").feature("cat1").feature().create("srcc1", "SurfaceReactionCatalyst");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("DiffusionCoefficientSource", "chem");
    model.component("comp2").physics("tds").feature("cat1").set("SurfaceSpecies", new String[]{"mp", "mpd"});
    model.component("comp2").physics("tds").feature("cat1")
         .set("M", new String[]{"comp2.chem.M_mp_surf", "comp2.chem.M_mpd_surf"});
    model.component("comp2").physics("tds").feature("cat1")
         .set("initialValues", new String[]{"1e-5[mol/m^2]", "1e-5[mol/m^2]"});
    model.component("comp2").physics("tds").feature("cat1").set("Sarea", "Ssa");
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .set("Rsurf_cd_src", "root.comp2.chem.Rsurf_d");
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .set("Rsurf_ce_src", "root.comp2.chem.Rsurf_e");
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .set("Rsurf_cp_src", "root.comp2.chem.Rsurf_p");
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .set("Rsurf_cpd_src", "root.comp2.chem.Rsurf_pd");
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .setIndex("Rssurf_src", "comp2.chem.R_mp_surf", 0, 0);
    model.component("comp2").physics("tds").feature("cat1").feature("srcc1")
         .setIndex("Rssurf_src", "comp2.chem.R_mpd_surf", 1, 0);
    model.component("comp2").physics("tds").feature("init1").set("initc", new String[]{"0", "ce_init", "0", "0"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"cd", "ce", "UserDefined", "cp", "cpd"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684", "c_solv", "\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cd", "ce", "c_solv", "cp", "cpd"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesSource", "PorousCatalyst_cat1");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "tds.csurf_mp", 0, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "tds.csurf_mpd", 1, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("tds").feature("sp1")
         .set("z", new String[]{"root.comp2.chem.z_d", "root.comp2.chem.z_e", "root.comp2.chem.z_p", "root.comp2.chem.z_pd"});
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("DiffusionCoefficientSource", "chem");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1").set("Dchem_cd_src", "userdef");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1").set("Dchem_ce_src", "userdef");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1").set("Dchem_cp_src", "userdef");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1").set("Dchem_cpd_src", "userdef");
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
    model.component("comp2").physics("br").feature("porous1").feature("fluid1")
         .set("rho_mat", "root.comp2.chem.rho");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "root.comp2.br.pA");
    model.component("comp2").physics("br").feature("porous1").feature("fluid1").set("mu_mat", "root.comp2.chem.eta");

    model.study("std2").feature("time").setSolveFor("/physics/re", false);
    model.study("std2").feature("time").setSolveFor("/physics/chem", true);
    model.study("std2").feature("time").setSolveFor("/physics/tds", true);
    model.study("std2").feature("time").setSolveFor("/physics/br", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/rfd1", true);
    model.study("std1").feature("time").setSolveFor("/physics/chem", false);
    model.study("std1").feature("time").setSolveFor("/physics/tds", false);
    model.study("std1").feature("time").setSolveFor("/physics/br", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/rfd1", false);

    model.component("comp2").geom("geom1").lengthUnit("mm");
    model.component("comp2").geom("geom1").create("r1", "Rectangle");
    model.component("comp2").geom("geom1").feature("r1").set("size", new int[]{6, 9});
    model.component("comp2").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp2").geom("geom1").feature("r2").set("size", new int[]{1, 9});
    model.component("comp2").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp2").geom("geom1").feature("r3").set("size", new int[]{2, 6});
    model.component("comp2").geom("geom1").feature("r3").set("pos", new int[]{1, 0});
    model.component("comp2").geom("geom1").runPre("fin");
    model.component("comp2").geom("geom1").run();

    model.component("comp2").material().remove("pmat1");

    model.component("comp2").physics().remove("br");

    model.component("comp2").multiphysics().remove("rfd1");

    model.component("comp2").physics("chem").feature("d").set("M", "Mnd");
    model.component("comp2").physics("chem").feature("mp_surf").set("M", "Mnp");
    model.component("comp2").physics("chem").feature("mpd_surf").set("M", "Mnpd");
    model.component("comp2").physics("chem").feature("p").set("M", "Mnp");
    model.component("comp2").physics("chem").feature("pd").set("M", "Mnpd");
    model.component("comp2").physics("chem").feature("e").set("M", "Mne");
    model.component("comp2").physics("chem").feature("h2o").set("M", "Mnh2o");
    model.component("comp2").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp2").physics("tds").feature("cat1").set("enableAdsorption", false);
    model.component("comp2").physics("tds").feature("cat1").setIndex("initialValues", "cmp_init", 0, 0);
    model.component("comp2").physics("tds").feature("cat1").setIndex("initialValues", "cmpd_init", 1, 0);
    model.component("comp2").physics("tds").feature("cat1")
         .label("\u591a\u5b54\u50ac\u5316\u5242 - \u751f\u7269\u6750\u6599");
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("Dchem_cd", new String[]{"Dd", "0", "0", "0", "Dd", "0", "0", "0", "Dd"});
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("Dchem_ce", new String[]{"De", "0", "0", "0", "De", "0", "0", "0", "De"});
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("Dchem_cp", new String[]{"Dp", "0", "0", "0", "Dp", "0", "0", "0", "Dp"});
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("Dchem_cpd", new String[]{"Dpd", "0", "0", "0", "Dpd", "0", "0", "0", "Dpd"});
    model.component("comp2").physics("tds").feature("cat1").feature("fluid1")
         .set("FluidDiffusivityModelType", "UserDefined");
    model.component("comp2").physics("tds").feature("cat1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp2").physics("tds").feature("cat1").feature("pm1").set("poro", "epsBio");
    model.component("comp2").physics("tds").create("cdm1", "Fluid", 2);
    model.component("comp2").physics("tds").feature("cdm1").selection().set(3);
    model.component("comp2").physics("tds").feature("cdm1")
         .label("\u4f20\u9012\u5c5e\u6027 - \u5468\u56f4\u73af\u5883");
    model.component("comp2").physics("tds").feature("cdm1")
         .set("D_cd", new String[]{"Dd_s", "0", "0", "0", "Dd_s", "0", "0", "0", "Dd_s"});
    model.component("comp2").physics("tds").feature("cdm1")
         .set("D_ce", new String[]{"De_s", "0", "0", "0", "De_s", "0", "0", "0", "De_s"});
    model.component("comp2").physics("tds").feature("cdm1")
         .set("D_cp", new String[]{"Dp_s", "0", "0", "0", "Dp_s", "0", "0", "0", "Dp_s"});
    model.component("comp2").physics("tds").feature("cdm1")
         .set("D_cpd", new String[]{"Dpd_s", "0", "0", "0", "Dpd_s", "0", "0", "0", "Dpd_s"});
    model.component("comp2").physics("tds").feature().duplicate("cdm2", "cdm1");
    model.component("comp2").physics("tds").feature("cdm2").selection().set(1);
    model.component("comp2").physics("tds").feature("cdm2").label("\u4f20\u9012\u5c5e\u6027 - \u795e\u7ecf");
    model.component("comp2").physics("tds").feature("cdm2")
         .set("D_cd", new String[]{"Dd_n", "0", "0", "0", "Dd_n", "0", "0", "0", "Dd_n"});
    model.component("comp2").physics("tds").feature("cdm2")
         .set("D_ce", new String[]{"De_n", "0", "0", "0", "De_n", "0", "0", "0", "De_n"});
    model.component("comp2").physics("tds").feature("cdm2")
         .set("D_cp", new String[]{"Dp_n", "0", "0", "0", "Dp_n", "0", "0", "0", "Dp_n"});
    model.component("comp2").physics("tds").feature("cdm2")
         .set("D_cpd", new String[]{"Dpd_n", "0", "0", "0", "Dpd_n", "0", "0", "0", "Dpd_n"});
    model.component("comp2").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cd_src", "root.comp2.chem.R_d", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_ce_src", "root.comp2.chem.R_e", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cp_src", "root.comp2.chem.R_p", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cpd_src", "root.comp2.chem.R_pd", 0);
    model.component("comp2").physics("tds").feature("reac1").selection().all();
    model.component("comp2").physics("tds").create("init2", "init", 2);
    model.component("comp2").physics("tds").feature("init2").selection().set(2, 3);

    model.component("comp2").mesh("mesh1").automatic(false);
    model.component("comp2").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp2").mesh("mesh1").run("size");
    model.component("comp2").mesh("mesh1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp2").mesh("mesh1").feature("size1").selection().set(4, 7, 9);
    model.component("comp2").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh1").feature("size1").set("hmax", 0.1);
    model.component("comp2").mesh("mesh1").run("ftri1");
    model.component("comp2").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp2").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh1").feature("bl1").feature("blp").selection().set(4, 6, 7, 9);
    model.component("comp2").mesh("mesh1").run();

    model.study("std2").feature("time").set("tunit", "h");
    model.study("std2").feature("time").set("tlist", "0 1/60 2/60 5/60 10/60 20/60 range(0.5,0.25,16)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 69, 0);
    model.result("pg2").label("\u6d53\u5ea6, d (tds)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"cd"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").set("typeintitle", true);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"tds.tflux_cdr", "tds.tflux_cdz"});
    model.result("pg2").feature("arws1").set("xnumber", 10);
    model.result("pg2").feature("arws1").set("ynumber", 10);
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").feature("arws1").create("sel1", "Selection");
    model.result("pg2").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset2");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "rev1");
    model.result("pg3").setIndex("looplevel", 69, 0);
    model.result("pg3").label("\u6d53\u5ea6, d, 3D (tds)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cd"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 69, 0);
    model.result("pg4").label("\u6d53\u5ea6, e (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"ce"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tds.tflux_cer", "tds.tflux_cez"});
    model.result("pg4").feature("arws1").set("xnumber", 10);
    model.result("pg4").feature("arws1").set("ynumber", 10);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "rev1");
    model.result("pg5").setIndex("looplevel", 69, 0);
    model.result("pg5").label("\u6d53\u5ea6, e, 3D (tds)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"ce"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 69, 0);
    model.result("pg6").label("\u6d53\u5ea6, p (tds)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cp"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"tds.tflux_cpr", "tds.tflux_cpz"});
    model.result("pg6").feature("arws1").set("xnumber", 10);
    model.result("pg6").feature("arws1").set("ynumber", 10);
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").feature("arws1").create("sel1", "Selection");
    model.result("pg6").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "rev1");
    model.result("pg7").setIndex("looplevel", 69, 0);
    model.result("pg7").label("\u6d53\u5ea6, p, 3D (tds)");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"cp"});
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 69, 0);
    model.result("pg8").label("\u6d53\u5ea6, pd (tds)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"cpd"});
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").create("arws1", "ArrowSurface");
    model.result("pg8").feature("arws1").set("expr", new String[]{"tds.tflux_cpdr", "tds.tflux_cpdz"});
    model.result("pg8").feature("arws1").set("xnumber", 10);
    model.result("pg8").feature("arws1").set("ynumber", 10);
    model.result("pg8").feature("arws1").set("color", "black");
    model.result("pg8").feature("arws1").create("sel1", "Selection");
    model.result("pg8").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "rev1");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg9").setIndex("looplevel", 69, 0);
    model.result("pg9").label("\u6d53\u5ea6, pd, 3D (tds)");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"cpd"});
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg2").run();

    model.component("comp2").variable().create("var1");
    model.component("comp2").variable("var1").selection().geom("geom1", 2);
    model.component("comp2").variable("var1").selection().set(2);
    model.component("comp2").variable("var1").set("cmp", "tds.csurf_mp*Ssa");
    model.component("comp2").variable("var1")
         .descr("cmp", "\u57fa\u8d28\u7ed3\u5408\u80bd\uff0c\u4f53\u79ef\u6d53\u5ea6");
    model.component("comp2").variable("var1").set("cmpd", "tds.csurf_mpd*Ssa");
    model.component("comp2").variable("var1")
         .descr("cmpd", "\u57fa\u8d28\u7ed3\u5408\u80bd\u7c7b\u836f\u7269\uff0c\u4f53\u79ef\u6d53\u5ea6");
    model.component("comp2").variable("var1").label("\u751f\u7269\u6750\u6599\u6d53\u5ea6");

    model.sol("sol2").updateSolution();

    model.result("pg2").run();
    model.result("pg2").label("\u6d53\u5ea6\uff0c\u836f\u7269");
    model.result("pg4").run();
    model.result("pg4").label("\u6d53\u5ea6\uff0c\u9176");
    model.result("pg6").run();
    model.result("pg6").label("\u6d53\u5ea6\uff0c\u80bd");
    model.result("pg8").run();
    model.result("pg8").label("\u6d53\u5ea6\uff0c\u80bd\u7c7b\u836f\u7269");
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result().remove("pg7");
    model.result().remove("pg9");
    model.result("pg6").run();
    model.result("pg3").run();
    model.result("pg3").label("\u672c\u4f53\u6d53\u5ea6\uff0c\u4e09\u7ef4");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").set("arrayshape", "square");
    model.result("pg3").set("arrayplane", "yz");
    model.result("pg3").set("order", "columnmajor");
    model.result("pg3").set("relrowpadding", -2);
    model.result("pg3").set("relcolumnpadding", 0.5);
    model.result("pg3").feature("surf1").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("expr", "ce");
    model.result("pg3").feature("surf1").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf3", "surf1");
    model.result("pg3").feature().duplicate("surf4", "surf2");
    model.result("pg3").feature("surf3").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").set("expr", "cp");
    model.result("pg3").feature("surf4").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf4").set("expr", "cpd");
    model.result("pg3").feature("surf2").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("colortable", "Viridis");
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "rightdouble");
    model.result().dataset("rev1").set("startangle", 45);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("arraydim", "2");
    model.result("pg3").feature("ann1").set("text", "\u836f\u7269");
    model.result("pg3").feature("ann1").set("poszexpr", 11);
    model.result("pg3").feature("ann1").set("anchorpoint", "lowermiddle");
    model.result("pg3").feature("ann1").set("showpoint", false);
    model.result("pg3").feature("ann1").set("belongstoplotarray", false);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ann2", "ann1");
    model.result("pg3").feature("ann2").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("ann2").set("text", "\u9176");
    model.result("pg3").feature("ann2").set("poszexpr", -4);
    model.result("pg3").feature("ann1").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ann3", "ann1");
    model.result("pg3").feature().duplicate("ann4", "ann2");
    model.result("pg3").feature("ann3").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("ann3").set("text", "\u80bd");
    model.result("pg3").feature("ann3").set("posyexpr", 18);

    model.view("view2").set("showaxisorientation", false);

    model.result("pg3").feature("ann4").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("ann4").set("text", "\u80bd\u7c7b\u836f\u7269");
    model.result("pg3").feature("ann4").set("posyexpr", 18);
    model.result("pg3").run();

    model.view("view2").set("showgrid", false);
    model.view("view2").set("showaxisorientation", true);

    model.result("pg3").run();
    model.result().move("pg3", 3);
    model.result().move("pg3", 4);
    model.result().move("pg3", 5);
    model.result().duplicate("pg9", "pg3");
    model.result("pg9").run();
    model.result("pg9").label("\u57fa\u8d28\u6d53\u5ea6\uff0c\u4e09\u7ef4");
    model.result("pg9").set("arrayshape", "linear");
    model.result("pg9").set("arrayaxis", "z");
    model.result("pg9").set("relpadding", -2.5);
    model.result("pg9").feature("surf3").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature().remove("surf3");
    model.result("pg9").feature().remove("surf4");
    model.result("pg9").feature().remove("ann3");
    model.result("pg9").feature().remove("ann4");
    model.result("pg9").feature("ann1").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("expr", "cmp");
    model.result("pg9").feature("surf1").set("colortable", "Wave");
    model.result("pg9").feature("surf2").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("surf2").set("expr", "cmpd");
    model.result("pg9").feature("surf2").set("colortable", "Wave");
    model.result("pg9").feature("ann1").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("ann1").set("text", "\u57fa\u8d28\u7ed3\u5408\u80bd\u7c7b\u836f\u7269");
    model.result("pg9").feature("ann2").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("ann2").set("text", "\u57fa\u8d28\u7ed3\u5408\u80bd\u7c7b\u836f\u7269");
    model.result("pg9").run();
    model.result("pg9").feature("ann2").set("poszexpr", -3);
    model.result("pg9").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 3, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 6, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 3, 1, 1);
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").setIndex("genpoints", 1, 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", 3, 1, 0);
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").set("data", "cln1");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").set("expr", "cpd+cd");
    model.result("pg10").feature("lngr1").set("linewidth", 2);
    model.result("pg10").feature("lngr1").set("linemarker", "cycle");
    model.result("pg10").feature("lngr1").set("markerpos", "interp");
    model.result("pg10").feature("lngr1").set("markers", 6);
    model.result("pg10").run();
    model.result("pg10").label("\u603b\u836f\u7269\u6d53\u5ea6");
    model.result("pg10").setIndex("looplevelinput", "interp", 0);
    model.result("pg10").setIndex("interp", "0 0.1 0.5 range(1,1,8)", 0);
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "c<sub>drug</sub> + c<sub>peptide-drug</sub>");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg10").feature("lngr1").set("legendpattern", "t = eval(t/3600) h");
    model.result("pg10").run();
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").run();
    model.result("pg10").set("titletype", "manual");
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature("lngr1").set("expr", "cmp+cmpd");
    model.result("pg11").run();
    model.result("pg11").label("\u603b\u57fa\u8d28\u6d53\u5ea6");
    model.result("pg11").set("title", "c<sub>mp</sub> + c<sub>mpd</sub>");
    model.result("pg11").run();
    model.result("pg11").set("titletype", "manual");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().set(2);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "(cd+cpd)*epsBio", 0);
    model.result().evaluationGroup("eg1").feature("int1")
         .setIndex("descr", "\u751f\u7269\u6750\u6599\u4e2d\u7684\u81ea\u7531\u836f\u7269", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int2", "int1");
    model.result().evaluationGroup("eg1").feature("int2").setIndex("expr", "cmpd", 0);
    model.result().evaluationGroup("eg1").feature("int2")
         .setIndex("descr", "\u57fa\u8d28\u7ed3\u5408\u836f\u7269", 0);
    model.result().evaluationGroup("eg1").create("int3", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int3").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int3").selection().set(1);
    model.result().evaluationGroup("eg1").feature("int3").setIndex("expr", "cd+cpd", 0);
    model.result().evaluationGroup("eg1").feature("int3")
         .setIndex("descr", "\u795e\u7ecf\u4e2d\u7684\u836f\u7269", 0);
    model.result().evaluationGroup("eg1").create("int4", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int4").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int4").selection().set(3);
    model.result().evaluationGroup("eg1").feature("int4").setIndex("expr", "cd+cpd", 0);
    model.result().evaluationGroup("eg1").feature("int4")
         .setIndex("descr", "\u5468\u56f4\u73af\u5883\u4e2d\u7684\u836f\u7269", 0);
    model.result().evaluationGroup("eg1").set("type", "general");
    model.result().evaluationGroup("eg1").set("generalheader", "\u603b\u836f\u7269\u6d53\u5ea6");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").set("keepchildnodes", true);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").set("data", "none");
    model.result("pg12").create("tblp1", "Table");
    model.result("pg12").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg12").feature("tblp1").set("evaluationgroup", "eg1");
    model.result("pg12").feature("tblp1").set("linewidth", "preference");
    model.result("pg12").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg12").run();
    model.result("pg12").feature("tblp1").set("linewidth", 2);
    model.result("pg12").feature("tblp1").set("legend", true);
    model.result("pg12").run();
    model.result("pg12").set("legendpos", "middleright");
    model.result("pg12").label("\u836f\u7269\u5206\u5e03");
    model.result("pg12").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").label("\u52a8\u753b - \u672c\u4f53\u6d53\u5ea6");
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("looplevelinput", "interp");
    model.result().export("anim1").set("interp", "range(0,0.5,16)");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u52a8\u753b - \u57fa\u8d28\u6d53\u5ea6");
    model.result().export("anim2").set("plotgroup", "pg9");
    model.result().export("anim2").run();

    model.title("\u4ece\u751f\u7269\u6750\u6599\u57fa\u8d28\u4e2d\u91ca\u653e\u836f\u7269");

    model
         .description("\u672c\u4f8b\u8be6\u7ec6\u7814\u7a76\u4e86\u836f\u7269\u91ca\u653e\u7684\u52a8\u529b\u5b66\u8fc7\u7a0b\uff0c\u6db5\u76d6\u4e86\u5904\u7406\u836f\u7269\u89e3\u79bb/\u7f14\u5408\u53cd\u5e94\u7684\u901f\u7387\u8868\u8fbe\u5f0f\uff0c\u4ee5\u53ca\u57fa\u8d28\u5728\u9176\u50ac\u5316\u4f5c\u7528\u4e0b\u7684\u964d\u89e3\u3002\u9176\u53cd\u5e94\u901a\u8fc7\u7c73\u6c0f\u52a8\u529b\u5b66\u8fdb\u884c\u63cf\u8ff0\u3002\u672c\u6a21\u578b\u7814\u7a76\u4e86\u5f71\u54cd\u836f\u7269\u91ca\u653e\u901f\u7387\u7684\u591a\u4e2a\u8bbe\u8ba1\u53c2\u6570\uff1a\u836f\u7269\u4e0e\u751f\u7269\u6750\u6599\u7684\u4eb2\u548c\u529b\u3001\u751f\u7269\u6750\u6599\u7684\u964d\u89e3\u3001\u8f7d\u836f\u91cf\uff0c\u4ee5\u53ca\u751f\u7269\u6750\u6599\u57fa\u8d28\u7684\u51e0\u4f55\u7ed3\u6784\u4e0e\u6210\u5206\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("drug_release.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
