/*
 * cubic_autocatalysis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:17 by COMSOL 6.3.0.290. */
public class cubic_autocatalysis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Ideal_Tank_Reactors");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("k", "0.0609[1/s]", "\u901f\u7387\u5e38\u6570");
    model.param().set("f", "0.06[1/s]", "\u8fdb\u7ed9\u7387");
    model.param().set("l", "1[m]", "\u957f\u5ea6");
    model.param().set("c0", "1[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6");
    model.param().set("DA", "2e-5[m^2/s]", "A \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DB", "1e-5[m^2/s]", "B \u7684\u6269\u6563\u7cfb\u6570");

    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "A+2B=>3B");
    model.component("comp1").physics("re").feature("B").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "B=>0B");
    model.component("comp1").physics("re").feature("rch2").set("kf", "f+k");
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "A=>0A");
    model.component("comp1").physics("re").feature("rch3").set("kf", "f");
    model.component("comp1").physics("re").create("add1", "AdditionalSourceFeature", -1);
    model.component("comp1").physics("re").feature("add1").setIndex("AddR", "f", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", 1, 1, 0);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "k", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/s", 0);
    model.study("std1").feature("param").setIndex("pname", "k", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0.0602, 1e-5, 0.0603)", 0);
    model.study("std1").feature("time").set("tlist", "range(0,5,500)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_A", "re.c_B"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").feature("col1").set("expr", "k-0.0602");
    model.result("pg1").feature("glob1").feature("col1").set("colortable", "Viridis");
    model.result("pg1").feature("glob1").feature("col1").set("colortabletrans", "reverse");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1").setIndex("expr", "re.c_A", 0);
    model.result("pg1").feature("glob1").set("legend", false);
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").setIndex("expr", "re.c_B", 0);
    model.result("pg1").feature("glob2").set("linestyle", "dashdot");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("posxexpr", 150);
    model.result("pg1").feature("ann1").set("posyexpr", 0.593);
    model.result("pg1").feature("ann1").set("text", "\u5b9e\u7ebf\uff1a[A]");
    model.result("pg1").feature("ann1").set("anchorpoint", "lowerleft");
    model.result("pg1").feature().duplicate("ann2", "ann1");
    model.result("pg1").run();
    model.result("pg1").feature("ann2").set("text", "\u865a\u7ebf\uff1a[B]");
    model.result("pg1").feature("ann2").set("posyexpr", 0.203);
    model.result("pg1").feature("ann2").set("anchorpoint", "upperleft");
    model.result("pg1").run();

    model.component("comp1").physics("re").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "2D");
    model.component("comp1").physics("re").feature("sync1").set("study", "Transient");
    model.component("comp1").physics("re").prop("synchronize").set("synchronize", "1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 2);
    model.component("comp2").geom("geom1").axisymmetric(false);
    model.component("comp2").geom("geom1").label("\u51e0\u4f55 1(2D)");

    model.component("comp2").mesh().create("mesh1");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");

    model.component("comp1").physics("re").feature("sync1").set("genom", new String[]{"comp2:geom1"});
    model.component("comp1").physics("re").feature("sync1").set("studyname", new String[]{"comp2:std2"});
    model.component("comp2").physics().create("chem", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem", 0);
    model.component("comp2").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T", "293.15[K]");
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
         .set("SpeciesrateUserDefinedList", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "1");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "2");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "2");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", "0");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventIsSet", "0");
    model.component("comp2").physics("chem").create("rch1", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch1").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").feature("rch1").set("formula", "A + 2 B => 3 B");
    model.component("comp2").physics("chem").feature("rch1").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch1").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch1").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch1").set("bulkFwdOrder", "3");
    model.component("comp2").physics("chem").feature("rch1").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch1").label("1: A + 2 B => 3 B");
    model.component("comp2").physics("chem").feature("rch1").set("rClass", "volumetric");
    model.component("comp2").physics("chem").create("rch2", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch2").set("rSequenceNo", "2");
    model.component("comp2").physics("chem").feature("rch2").set("formula", "B => 0 B");
    model.component("comp2").physics("chem").feature("rch2").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch2").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch2").set("kf", "f+k");
    model.component("comp2").physics("chem").feature("rch2").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch2").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch2").label("2: B => 0 B");
    model.component("comp2").physics("chem").feature("rch2").set("rClass", "volumetric");
    model.component("comp2").physics("chem").create("rch3", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch3").set("rSequenceNo", "3");
    model.component("comp2").physics("chem").feature("rch3").set("formula", "A => 0 A");
    model.component("comp2").physics("chem").feature("rch3").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch3").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch3").set("kf", "f");
    model.component("comp2").physics("chem").feature("rch3").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch3").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch3").label("3: A => 0 A");
    model.component("comp2").physics("chem").feature("rch3").set("rClass", "volumetric");
    model.component("comp2").physics("chem").feature("A").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("A").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("A").set("specLabel", "A");
    model.component("comp2").physics("chem").feature("A").set("speciesNameInput", "A");
    model.component("comp2").physics("chem").feature("A").set("specName", "A");
    model.component("comp2").physics("chem").feature("A").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("A").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("A").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("A").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("A").set("z", "0");
    model.component("comp2").physics("chem").feature("A").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("A").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("A").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("A").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("A").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("A").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("A").set("cLock", "0");
    model.component("comp2").physics("chem").feature("A").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("A").set("dependent", "0");
    model.component("comp2").physics("chem").feature("A").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("A").set("AdditionalSource", "1");
    model.component("comp2").physics("chem").feature("A").set("AddR", "f");
    model.component("comp2").physics("chem").feature("A").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("A").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("A").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("A").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("B").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("B").set("specLabel", "B");
    model.component("comp2").physics("chem").feature("B").set("speciesNameInput", "B");
    model.component("comp2").physics("chem").feature("B").set("specName", "B");
    model.component("comp2").physics("chem").feature("B").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("B").set("chemicalFormula", "B");
    model.component("comp2").physics("chem").feature("B").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("B").set("M", "10.8135[g/mol]");
    model.component("comp2").physics("chem").feature("B").set("z", "0");
    model.component("comp2").physics("chem").feature("B").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("B").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("B").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("B").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("B").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("B").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("B").set("cLock", "0");
    model.component("comp2").physics("chem").feature("B").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("B").set("dependent", "0");
    model.component("comp2").physics("chem").feature("B").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("B").set("AdditionalSource", "1");
    model.component("comp2").physics("chem").feature("B").set("AddR", "0");
    model.component("comp2").physics("chem").feature("B").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("B").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("B").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("B").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").prop("simpropChem").set("rSequenceNo", "3");
    model.component("comp2").physics("chem").prop("simpropChem").set("sSequenceNo", "2");
    model.component("comp2").physics("chem").prop("mixture").set("hasPropertyPackage", "0");
    model.component("comp2").physics("chem").prop("mixture").set("PropertyPackage", "");
    model.component("comp2").physics("chem").prop("mixture").set("Thermodynamics", "0");
    model.component("comp2").physics("tds").field("concentration").component(new String[]{"cA", "cB"});
    model.component("comp2").physics("tds").feature("init1").set("initc", new String[]{"0", "1"});
    model.component("comp2").physics("tds").feature().create("reac1", "Reactions");
    model.component("comp2").physics("tds").feature("reac1").selection().all();
    model.component("comp2").physics("tds").feature("reac1").set("ReactingVolumeType", "TotalVolume");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"cA", "cB"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cA", "cB"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("csurf", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("tds").feature("sp1")
         .set("z", new String[]{"root.comp2.chem.z_A", "root.comp2.chem.z_B"});
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cA", "1e-5[m^2/s]");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cA", "1e-5[m^2/s]");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cB", "1e-5[m^2/s]");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cB", "1e-5[m^2/s]");
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cA_src", "root.comp2.chem.R_A", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cB_src", "root.comp2.chem.R_B", 0);
    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re").feature("sync1").set("chemTag", "chem");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "tds");

    model.study("std2").feature("time").setSolveFor("/physics/re", false);
    model.study("std2").feature("time").setSolveFor("/physics/chem", true);
    model.study("std2").feature("time").setSolveFor("/physics/tds", true);
    model.study("std1").feature("param").setSolveFor("/physics/chem", false);
    model.study("std1").feature("param").setSolveFor("/physics/tds", false);
    model.study("std1").feature("time").setSolveFor("/physics/chem", false);
    model.study("std1").feature("time").setSolveFor("/physics/tds", false);

    model.component("comp2").variable().create("var1");

    model.component("comp2").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp2").variable("var1").loadFile("FILENAME");
    model.component("comp2").variable("var1")
         .set("cA0", "c0*(0.4201+0.2*(sa1+sa2+sb+sc))", "\u521d\u59cb\u6d53\u5ea6 A");
    model.component("comp2").variable("var1").set("cB0", "c0*0.34 - 0.1*cA0", "\u521d\u59cb\u6d53\u5ea6 B");
    model.component("comp2").variable("var1")
         .set("ra", "sqrt((x-0.5)^2+(y-0.5)^2)", "\u534a\u5f84 a\uff08\u4ece\u4e2d\u5fc3\u5f00\u59cb\uff09");
    model.component("comp2").variable("var1")
         .set("rb", "sqrt((x-0.25)^2+(y-0.97)^2)", "\u534a\u5f84 b\uff08\u4ece\u7b2c 1 \u4e2a\u5c0f\u4f4d\u70b9\u5f00\u59cb\uff09");
    model.component("comp2").variable("var1")
         .set("rc", "sqrt((x-0.75)^2+(y-0.97)^2)", "\u534a\u5f84 c\uff08\u4ece\u7b2c 2 \u4e2a\u5c0f\u4f4d\u70b9\u5f00\u59cb\uff09");
    model.component("comp2").variable("var1").set("sa1", "exp(-(20*(ra-0.13*l))^4/l^4)", "\u5185\u73af\u524d\u90e8");
    model.component("comp2").variable("var1").set("sa2", "exp(-(20*(ra-0.3*l))^4/l^4)", "\u5916\u73af\u524d\u90e8");
    model.component("comp2").variable("var1").set("sb", "exp(-(20*(rb-0.11*l))^4/l^4)", "\u536b\u661f b");
    model.component("comp2").variable("var1").set("sc", "exp(-(20*(rc-0.11*l))^4/l^4)", "\u536b\u661f c");

    model.component("comp2").geom("geom1").create("sq1", "Square");
    model.component("comp2").geom("geom1").feature("sq1").set("size", "2*l");
    model.component("comp2").geom("geom1").feature("sq1").set("pos", new String[]{"-l/2", "-l/2"});
    model.component("comp2").geom("geom1").run();

    model.component("comp2").physics("chem").prop("calcTransport").set("calcTransport", false);
    model.component("comp2").physics("tds").feature("init1").setIndex("initc", "cA0", 0);
    model.component("comp2").physics("tds").feature("init1").setIndex("initc", "cB0", 1);
    model.component("comp2").physics("tds").create("pc1", "PeriodicCondition", 1);
    model.component("comp2").physics("tds").feature("pc1").selection().set(2, 3);
    model.component("comp2").physics("tds").create("pc2", "PeriodicCondition", 1);
    model.component("comp2").physics("tds").feature("pc2").selection().set(1, 4);
    model.component("comp2").physics("tds").feature("cdm1")
         .set("D_cA", new String[]{"DA", "0", "0", "0", "DA", "0", "0", "0", "DA"});
    model.component("comp2").physics("tds").feature("cdm1")
         .set("D_cB", new String[]{"DB", "0", "0", "0", "DB", "0", "0", "0", "DB"});

    model.component("comp2").mesh("mesh1").create("map1", "Map");
    model.component("comp2").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 200);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp2").mesh("mesh1").run();

    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "k", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "1/s", 0);
    model.study("std2").feature("param").setIndex("pname", "k", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "1/s", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.0609 0.06105 0.0611", 0);
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol().create("sol15");
    model.sol("sol15").study("std2");
    model.sol("sol15").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol15");

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").label("\u6d53\u5ea6, A (tds)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"cA"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").set("typeintitle", true);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"tds.tflux_cAx", "tds.tflux_cAy"});
    model.result("pg2").feature("arws1").set("xnumber", 10);
    model.result("pg2").feature("arws1").set("ynumber", 10);
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").feature("arws1").create("sel1", "Selection");
    model.result("pg2").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").label("\u6d53\u5ea6, B (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cB"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"tds.tflux_cBx", "tds.tflux_cBy"});
    model.result("pg3").feature("arws1").set("xnumber", 10);
    model.result("pg3").feature("arws1").set("ynumber", 10);
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").feature("arws1").create("sel1", "Selection");
    model.result("pg3").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("arws1");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result("pg2").run();
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").set("arrayshape", "square");
    model.result("pg2").feature("surf1").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").label("cA");
    model.result("pg2").feature("surf1").set("manualindexing", true);
    model.result("pg2").feature("surf1").set("colindex", 1);
    model.result("pg2").feature("surf1").create("hght1", "Height");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("hght1").set("scale", "1.0");
    model.result("pg2").feature("surf1").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortable", "Cividis");
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").feature("surf2").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").label("cB");
    model.result("pg2").feature("surf2").set("expr", "cB");
    model.result("pg2").feature("surf2").set("colortable", "Viridis");
    model.result("pg2").feature("surf2").set("colindex", 0);

    model.study("std2").feature("time")
         .set("tlist", "range(0, 1, 10-1) range(10, 10, 300-10) range(300, 100, 1000-100) range(1e3, 1e3, 2e4-1e3) range(2e4, 2.5e3, 5e4-2.5e3) range(5e4, 1e4, 1e5-1e4) range(1e5, 2.5e4, 6e5)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", 0.001);
    model.study("std2").feature("time").set("plot", true);
    model.study("std2").feature("time").set("plotgroup", "pg2");
    model.study("std2").createAutoSequences("all");

    model.batch("p2").run("compute");

    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").set("legendpos", "alternating");
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result().export("anim1").setIndex("singlelooplevel", 2, 1);
    model.result().export("anim1").run();

    model.title("\u4e09\u6b21\u81ea\u50ac\u5316\uff1a\u63a2\u7d22 Gray-Scott \u6a21\u578b");

    model
         .description("\u672c\u4f8b\u5206\u6790\u4e00\u4e2a\u770b\u4f3c\u7b80\u5355\u7684\u7531\u4e24\u79cd\u7269\u8d28\u548c\u4e24\u4e2a\u53cd\u5e94\u6784\u6210\u7684\u7cfb\u7edf\uff0c\u7528\u4e8e\u63cf\u8ff0\u4e00\u79cd\u7269\u8d28\u7684\u81ea\u50ac\u5316\u8f6c\u5316\u8fc7\u7a0b\uff0c\u8868\u73b0\u51fa\u4ee4\u4eba\u60ca\u8bb6\u7684\u590d\u6742\u884c\u4e3a\u3002\u7814\u7a76\u4ee5\u96f6\u7ef4\u5168\u6df7\u6d41\u53cd\u5e94\u5668 (CSTR) \u63cf\u8ff0\u4e3a\u57fa\u7840\uff0c\u5efa\u7acb\u4e86\u4e8c\u7ef4\u53cd\u5e94-\u6269\u6563\u95ee\u9898\uff0c\u63ed\u793a\u4e86\u7531\u5e95\u5c42\u53cd\u5e94\u7f51\u7edc\u7684\u591a\u7a33\u5b9a\u6027\u4ea7\u751f\u7684\u4e30\u5bcc\u6a21\u5f0f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();

    model.label("cubic_autocatalysis.mph");

    return model;
  }

  public static Model run2(Model model) {

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
