/*
 * carbon_deposition.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:25 by COMSOL 6.3.0.290. */
public class carbon_deposition {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Porous_Catalysts");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "CH4=>C+2H2");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "a");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("u_in", "3[cm/s]", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6");
    model.param().set("c_CH4in", "15[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0cCH4");
    model.param().set("c_H2in", "0[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0cH2");
    model.param().set("rho", "1[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6d41\u4f53");
    model.param().set("rho_cat", "3630[kg/m^3]", "\u5bc6\u5ea6\uff0cNi Al2O3 \u50ac\u5316\u5242");
    model.param().set("eta", "1e-5[Pa*s]", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u6d41\u4f53");
    model.param().set("por0", "0.4", "\u5b54\u9699\u7387\uff0c\u6e05\u6d01\u50ac\u5316\u5242");
    model.param().set("kappa0", "1e-9[m^2]", "\u6e17\u900f\u7387\uff0c\u6e05\u6d01\u50ac\u5316\u5242");
    model.param().set("rho_soot", "4e3[kg/m^3]", "\u5bc6\u5ea6\uff0c\u70df\u7070\u6c89\u79ef");
    model.param().set("M_a", "161[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0ca");
    model.param().set("D_CH4H2", "4e-6[m^2/s]", "\u4e8c\u5143\u6269\u6563\u7cfb\u6570");
    model.param().set("Cp_CH4", "2230[J/(kg*K)]", "\u6bd4\u70ed\u5bb9\uff0cCH4");
    model.param().set("Cp_C", "710[J/(kg*K)]", "\u6bd4\u70ed\u5bb9\uff0cC");
    model.param().set("Cp_H2", "14400[J/(kg*K)]", "\u6bd4\u70ed\u5bb9\uff0cH2");
    model.param().set("Cp_cat", "500[J/(kg*K)]", "\u6bd4\u70ed\u5bb9\uff0cNi Al2O3 \u50ac\u5316\u5242");
    model.param().set("h_CH4", "-7.46e5[J/mol]", "\u6469\u5c14\u751f\u6210\u7113\uff0cCH4");
    model.param().set("h_C", "0[J/mol]", "\u6469\u5c14\u751f\u6210\u7113\uff0cCH4");
    model.param().set("h_H2", "0[J/mol]", "\u6469\u5c14\u751f\u6210\u7113\uff0cH2");
    model.param().set("s_CH4", "186.4[J/(mol*K)]", "\u6469\u5c14\u71b5\uff0cCH4");
    model.param().set("s_C", "5.74[J/(mol*K)]", "\u6469\u5c14\u71b5\uff0cC");
    model.param().set("s_H2", "130.7[J/(mol*K)]", "\u6469\u5c14\u71b5\uff0cH2");
    model.param().set("kt_CH4", "0.030[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0cCH4");
    model.param().set("kt_H2", "0.185[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0cH2");
    model.param().set("kt_C", "3[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0cC");
    model.param().set("kt_cat", "3[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0cNi Al2O3 \u50ac\u5316\u5242");
    model.param().set("amount_cat", "300[g/m^3]");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("k", "2.31e-5*exp(20.492-104200[J/mol]/(R_const*re.T))[s^-1]", "\u963f\u7d2f\u5c3c\u4e4c\u65af\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var1")
         .set("Kp", "5.088e5*exp(-91200[J/mol]/(R_const*re.T))", "\u963f\u7d2f\u5c3c\u4e4c\u65af\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var1")
         .set("kH", "exp(163200[J/mol]/(R_const*re.T)-22.426)", "\u963f\u7d2f\u5c3c\u4e4c\u65af\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var1")
         .set("ka", "6.238e6*exp(135600[J/mol]/(R_const*re.T)-32.077)[m^9*mol^-3*s]", "\u963f\u7d2f\u5c3c\u4e4c\u65af\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var1").set("P_CH4", "re.c_CH4*R_const*re.T/1[atm]", "CH4\uff0c\u5206\u538b");
    model.component("comp1").variable("var1")
         .set("P_H2", "max(re.c_H2*R_const*re.T/1[atm],eps)", "H2\uff0c\u5206\u538b");

    model.component("comp1").physics("re").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch1")
         .set("r", "re.c_a*k*(P_CH4-P_H2^2/Kp)/(1+kH*sqrt(P_H2))^2");
    model.component("comp1").physics("re").create("add1", "AdditionalSourceFeature", -1);
    model.component("comp1").physics("re").feature("add1").setIndex("AddR", "-ka*re.r_1^2*re.c_C*re.c_a", 3, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_CH4in", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_H2in", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", 1, 3, 0);
    model.component("comp1").physics("re").prop("energybalance").set("T", "850[K]");
    model.component("comp1").physics("re").prop("mixture").set("psource", "UserDefined");
    model.component("comp1").physics("re").prop("mixture").set("p", "R_const*re.T*(re.c_CH4+re.c_H2)");

    model.study("std1").feature("time").set("tlist", "range(0,500,20000)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_CH4", "re.c_C", "re.c_H2", "re.c_a"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();

    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("\u5b58\u5728\u50ac\u5316\u5242\u5931\u6d3b");

    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").label("\u50ac\u5316\u5242\u6d3b\u6027 (re)");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_a"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("legend", false);
    model.result("pg1").run();

    model.component("comp1").physics("re").feature("a").set("cLock", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.c_CH4", "re.c_C", "re.c_H2"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg2").label("\u6d53\u5ea6 (re)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg2").run();

    model.sol("sol1").copySolution("sol3");
    model.sol("sol3").label("\u6052\u5b9a\u50ac\u5316\u5242\u6d3b\u6027");

    model.result("pg2").run();
    model.result("pg2").label("\u6d53\u5ea6\u6bd4\u8f83 (re)");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").label("\u5b58\u5728\u50ac\u5316\u5242\u5931\u6d3b");
    model.result("pg2").feature("glob1").set("data", "dset2");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "CH<sub>4</sub> \u5931\u6d3b", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "C \u5931\u6d3b", 1);
    model.result("pg2").feature("glob1").setIndex("legends", "H<sub>2</sub> \u5931\u6d3b", 2);
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").label("\u6052\u5b9a\u50ac\u5316\u5242\u6d3b\u6027");
    model.result("pg2").feature("glob2").set("data", "dset3");
    model.result("pg2").feature("glob2").set("linestyle", "dashed");
    model.result("pg2").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg2").feature("glob2").setIndex("legends", "CH<sub>4</sub> \u6052\u5b9a\u6d3b\u6027", 0);
    model.result("pg2").feature("glob2").setIndex("legends", "C \u6052\u5b9a\u6d3b\u6027", 1);
    model.result("pg2").feature("glob2").setIndex("legends", "H<sub>2</sub> \u6052\u5b9a\u6d3b\u6027", 2);
    model.result("pg2").run();

    model.component("comp1").physics("re").feature("C").set("cLock", true);
    model.component("comp1").physics("re").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "2Daxi");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "ConcentratedSpecies");
    model.component("comp1").physics("re").feature("sync1").set("energybalance", "PorousMediaHeatTransfer");
    model.component("comp1").physics("re").feature("sync1").set("momentumbalance", "LaminarFlow");
    model.component("comp1").physics("re").prop("synchronize").set("synchronize", "1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 2);
    model.component("comp2").geom("geom1").axisymmetric(true);
    model.component("comp2").geom("geom1").label("\u51e0\u4f55 1(2Daxi)");

    model.component("comp2").mesh().create("mesh1");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");

    model.component("comp1").physics("re").feature("sync1").set("genom", new String[]{"comp2:geom1"});
    model.component("comp1").physics("re").feature("sync1").set("studyname", new String[]{"comp2:std2"});
    model.component("comp2").physics().create("chem", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem", 0);
    model.component("comp2").physics().create("tcs", "ConcentratedSpecies", "geom1");
    model.component("comp2").physics("tcs").prop("AdvancedSettings").set("UsePseudoTime", true);
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T", "850[K]");
    model.component("comp2").physics("chem").prop("mixture").set("mixture", "gas");
    model.component("comp2").physics("chem").prop("mixture").set("gasDensitySel", "Automatic");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput")
         .set("p", "R_const*chem.T*(chem.c_CH4+chem.c_H2)");
    model.component("comp2").physics("chem").prop("Activity").set("useActivity", "0");
    model.component("comp2").physics("chem").prop("chemkin").set("chemkin", "0");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("VolumetricConcentrationGlobalActivityStandardState", "1[mol/m^3]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceSpeciesConcentrationType", "SurfaceConcentration");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceGlobalActivityStandardState", "1[mol/m^2]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SpeciesrateUserDefinedList", new String[]{"C", "a"});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "1");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "4");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "4");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", "0");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventIsSet", "0");
    model.component("comp2").physics("chem").create("rch1", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch1").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").feature("rch1").set("formula", "CH4 => C + 2 H2");
    model.component("comp2").physics("chem").feature("rch1").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch1").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch1")
         .set("r", "chem.c_a*k*(P_CH4-P_H2^2/Kp)/(1+kH*sqrt(P_H2))^2");
    model.component("comp2").physics("chem").feature("rch1").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch1").set("bulkFwdOrder", "1");
    model.component("comp2").physics("chem").feature("rch1").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch1").label("1: CH4 => C + 2 H2");
    model.component("comp2").physics("chem").feature("rch1").set("rClass", "volumetric");
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("CH4").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("CH4").set("specLabel", "CH4");
    model.component("comp2").physics("chem").feature("CH4").set("speciesNameInput", "CH4");
    model.component("comp2").physics("chem").feature("CH4").set("specName", "CH4");
    model.component("comp2").physics("chem").feature("CH4").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("CH4").set("chemicalFormula", "CH4");
    model.component("comp2").physics("chem").feature("CH4").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("CH4").set("M", "16.04252[g/mol]");
    model.component("comp2").physics("chem").feature("CH4").set("z", "0");
    model.component("comp2").physics("chem").feature("CH4").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("CH4").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("CH4").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("CH4").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("CH4").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("CH4").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("CH4").set("cLock", "0");
    model.component("comp2").physics("chem").feature("CH4").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("CH4").set("dependent", "0");
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("CH4").set("AdditionalSource", "1");
    model.component("comp2").physics("chem").feature("CH4").set("AddR", "0");
    model.component("comp2").physics("chem").feature("CH4").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("CH4").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("CH4").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("CH4").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("CH4").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("C").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("C").set("specLabel", "C");
    model.component("comp2").physics("chem").feature("C").set("speciesNameInput", "C");
    model.component("comp2").physics("chem").feature("C").set("specName", "C");
    model.component("comp2").physics("chem").feature("C").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("C").set("chemicalFormula", "C");
    model.component("comp2").physics("chem").feature("C").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("C").set("M", "12.0106[g/mol]");
    model.component("comp2").physics("chem").feature("C").set("z", "0");
    model.component("comp2").physics("chem").feature("C").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("C").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("C").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("C").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("C").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("C").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("C").set("cLock", "1");
    model.component("comp2").physics("chem").feature("C").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("C").set("dependent", "0");
    model.component("comp2").physics("chem").feature("C").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("C").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("C").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("C").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("C").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("C").set("SpeciesThermoaHi7", new String[]{"0"});
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
    model.component("comp2").physics("chem").feature("H2").set("AdditionalSource", "1");
    model.component("comp2").physics("chem").feature("H2").set("AddR", "0");
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
    model.component("comp2").physics("chem").create("a", "SpeciesChem");
    model.component("comp2").physics("chem").feature("a").set("SpeciesSource", "FreeSpecies");
    model.component("comp2").physics("chem").feature("a").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("a").set("specLabel", "a");
    model.component("comp2").physics("chem").feature("a").set("speciesNameInput", "a");
    model.component("comp2").physics("chem").feature("a").set("specName", "a");
    model.component("comp2").physics("chem").feature("a").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("a").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("a").label("\u7269\u8d28: a (\u5e38\u6570)");
    model.component("comp2").physics("chem").feature("a").active(true);
    model.component("comp2").physics("chem").feature("a").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("a").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("a").set("z", "0");
    model.component("comp2").physics("chem").feature("a").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("a").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("a").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("a").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("a").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("a").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("a").set("cLock", "1");
    model.component("comp2").physics("chem").feature("a").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("a").set("dependent", "0");
    model.component("comp2").physics("chem").feature("a").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("a").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("a").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("a").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("a").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("a").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").prop("simpropChem").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").prop("simpropChem").set("sSequenceNo", "4");
    model.component("comp2").physics("chem").prop("mixture").set("hasPropertyPackage", "0");
    model.component("comp2").physics("chem").prop("mixture").set("PropertyPackage", "");
    model.component("comp2").physics("chem").prop("mixture").set("Thermodynamics", "0");
    model.component("comp2").physics("tcs").field("massfraction").component(new String[]{"wCH4", "wH2"});
    model.component("comp2").physics("tcs").feature("init1")
         .set("w0", new String[]{"chem.M_CH4*c_CH4in/(chem.M_CH4*c_CH4in+chem.M_H2*c_H2in)", "chem.M_H2*c_H2in/(chem.M_CH4*c_CH4in+chem.M_H2*c_H2in)"});
    model.component("comp2").physics("tcs").feature().create("reac1", "ReactionSources");
    model.component("comp2").physics("tcs").feature("reac1").selection().all();
    model.component("comp2").physics("tcs").feature("reac1").set("MassTransferToOtherPhases", true);
    model.component("comp2").physics("tcs").feature("reac1").set("ReactingVolumeType", "TotalVolume");
    model.component("comp2").physics("chem").prop("mixture").set("ConcentrationType", "MassFraction");
    model.component("comp2").physics("chem").prop("calcTransport").set("calcTransport", "1");
    model.component("comp2").physics("tcs").feature("cdm1").set("rho_src", "root.comp2.chem.rho_chem");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tcs");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "wCH4", "wH2", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"0", "\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684", "1"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"0", "cCH4", "cH2", "1"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("csurf", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("tcs").feature("sp1").set("M_wCH4_src", "root.comp2.chem.M_CH4");
    model.component("comp2").physics("tcs").feature("sp1").set("M_wH2_src", "root.comp2.chem.M_H2");
    model.component("comp2").physics("tcs").feature("sp1").set("z", new String[]{"0", "0"});
    model.component("comp2").physics("tcs").feature("cdm1")
         .set("Dik", new String[]{"1", "comp2.chem.D_CH4_H2", "comp2.chem.D_CH4_H2", "1"});
    model.component("comp2").physics("tcs").feature("cdm1").setIndex("DiffusivityFrom", "comp2.chem.D_CH4_H2", 0, 0);
    model.component("comp2").physics("tcs").feature("reac1").setIndex("R_wCH4_src", "root.comp2.chem.Rw_CH4", 0);
    model.component("comp2").physics("tcs").feature("reac1").setIndex("R_wH2_src", "root.comp2.chem.Rw_H2", 0);
    model.component("comp2").physics().create("spf", "LaminarFlow", "geom1");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp2").physics().create("ht", "PorousMediaHeatTransfer", "geom1");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").setIndex("fluidType", "idealGas", 0);
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .setIndex("gasConstantType", "numberAve", 0);
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").setIndex("gamma_mat", "userdef", 0);
    model.component("comp2").physics("ht").feature("init1").set("Tinit", "298.15[K]");
    model.component("comp2").physics("ht").feature().create("hs1", "HeatSource");
    model.component("comp2").physics("ht").feature("hs1").selection().all();
    model.component("comp2").physics("ht").feature("hs1").set("Q0_src", "root.comp2.chem.Qtot");

    model.component("comp2").multiphysics().create("nirf1", "NonIsothermalReactingFlow", 2);
    model.component("comp2").multiphysics("nirf1").set("Fluid_physics", "spf");
    model.component("comp2").multiphysics("nirf1").set("Species_physics", "tcs");
    model.component("comp2").multiphysics("nirf1").set("Chemistry_physics", "chem");
    model.component("comp2").multiphysics("nirf1").set("Heat_physics", "ht");

    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re").feature("sync1").set("chemTag", "chem");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "tcs");
    model.component("comp2").physics("tcs").feature("cdm1").set("minput_pressure_src", "root.comp2.spf.pA");
    model.component("comp2").physics("tcs").feature("cdm1").set("u_src", "root.comp2.u");
    model.component("comp2").physics("tcs").feature("cdm1").set("minput_temperature_src", "root.comp2.T");
    model.component("comp1").physics("re").feature("sync1").set("energybalance", "ht");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("gamma_not_IG_mat", "root.comp2.nirf1.gamma");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("u_src", "root.comp2.u");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("Cp_mat", "root.comp2.nirf1.Cp");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("minput_pressure_src", "root.comp2.spf.pA");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "root.comp2.T");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("k_mat", "root.comp2.nirf1.krr");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("rho_mat", "root.comp2.tcs.rho");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("molarmass_mat", "root.comp2.chem.M_CH4");
    model.component("comp2").physics("ht").feature("hs1").setIndex("Q0_src", "userdef", 0);
    model.component("comp2").physics("ht").feature("hs1").set("Q0", "(1-comp2.ht.porous.pm.theta)*comp2.chem.Qtot");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("Cp_mat", "root.comp2.chem.Cptot");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("heatcapacity_mat", "root.comp2.chem.Cptot");
    model.component("comp1").physics("re").feature("sync1").set("momentumbalance", "spf");
    model.component("comp2").physics("spf").feature("fp1").set("rho_mat", "root.comp2.tcs.rho");
    model.component("comp2").physics("spf").feature("fp1").set("mu_mat", "root.comp2.nirf1.mu");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "root.comp2.spf.pA");

    model.study("std2").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tcs", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nirf1", true);
    model.study("std1").feature("time").setSolveFor("/physics/chem", false);
    model.study("std1").feature("time").setSolveFor("/physics/tcs", false);
    model.study("std1").feature("time").setSolveFor("/physics/spf", false);
    model.study("std1").feature("time").setSolveFor("/physics/ht", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nirf1", false);

    model.component("comp2").variable().create("var2");

    model.component("comp2").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp2").variable("var2").loadFile("FILENAME");
    model.component("comp2").variable("var2")
         .set("k", "2.31e-5*amount_cat*1e3*exp(20.492-104200[J/mol]/(R_const*chem.T))[m^3*s^-1*kg^-1]", "\u963f\u7d2f\u5c3c\u4e4c\u65af\u8868\u8fbe\u5f0f");
    model.component("comp2").variable("var2")
         .set("Kp", "5.088e5*exp(-91200[J/mol]/(R_const*chem.T))", "\u963f\u7d2f\u5c3c\u4e4c\u65af\u8868\u8fbe\u5f0f");
    model.component("comp2").variable("var2")
         .set("kH", "exp(163200[J/mol]/(R_const*chem.T)-22.426)", "\u963f\u7d2f\u5c3c\u4e4c\u65af\u8868\u8fbe\u5f0f");
    model.component("comp2").variable("var2")
         .set("P_CH4", "chem.c_CH4*R_const*chem.T/1[atm]", "CH4\uff0c\u5206\u538b");
    model.component("comp2").variable("var2")
         .set("P_H2", "max(chem.c_H2*R_const*chem.T/1[atm],eps)", "H2\uff0c\u5206\u538b");
    model.component("comp2").variable("var2").set("kappa", "kappa0*(por/por0)^3.55", "\u6e17\u900f\u7387");
    model.component("comp2").variable("var2").set("Tvar", "T");

    model.component("comp2").geom("geom1").create("r1", "Rectangle");
    model.component("comp2").geom("geom1").feature("r1").set("size", new double[]{0.01, 0.04});
    model.component("comp2").geom("geom1").run("r1");
    model.component("comp2").geom("geom1").create("r2", "Rectangle");
    model.component("comp2").geom("geom1").feature("r2").set("size", new double[]{0.015, 0.12});
    model.component("comp2").geom("geom1").feature("r2").set("pos", new double[]{0, 0.04});
    model.component("comp2").geom("geom1").run("r2");
    model.component("comp2").geom("geom1").create("r3", "Rectangle");
    model.component("comp2").geom("geom1").feature("r3").set("size", new double[]{0.01, 0.04});
    model.component("comp2").geom("geom1").feature("r3").set("pos", new double[]{0, 0.16});
    model.component("comp2").geom("geom1").runPre("fin");
    model.component("comp2").geom("geom1").create("fil1", "Fillet");
    model.component("comp2").geom("geom1").feature("fil1").selection("point").set("r2", 2, 3);
    model.component("comp2").geom("geom1").feature("fil1").set("radius", 0.001);
    model.component("comp2").geom("geom1").runPre("fin");
    model.component("comp2").geom("geom1").create("uni1", "Union");
    model.component("comp2").geom("geom1").feature("uni1").selection("input").set("fil1", "r1", "r3");
    model.component("comp2").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp2").geom("geom1").runPre("fin");
    model.component("comp2").geom("geom1").create("fil2", "Fillet");
    model.component("comp2").geom("geom1").feature("fil2").selection("point").set("uni1", 6, 7);
    model.component("comp2").geom("geom1").feature("fil2").set("radius", 0.004);
    model.component("comp2").geom("geom1").run("fil2");
    model.component("comp2").geom("geom1").create("r4", "Rectangle");
    model.component("comp2").geom("geom1").feature("r4").set("size", new double[]{0.015, 0.112});
    model.component("comp2").geom("geom1").feature("r4").set("pos", new double[]{0, 0.044});
    model.component("comp2").geom("geom1").runPre("fin");

    model.component("comp2").physics().create("dode", "DomainODE", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/dode", true);
    model.study("std2").feature("stat").setSolveFor("/physics/dode", true);

    model.component("comp2").physics("dode").prop("EquationForm").set("form", "Automatic");

    model.component("comp2").geom("geom1").run();

    model.component("comp2").physics("dode").label("\u5b54\u9699\u7387\u53d8\u5316");
    model.component("comp2").physics("dode").selection().set(2);
    model.component("comp2").physics("dode").prop("Units").setIndex("CustomSourceTermUnit", "1/s", 0, 0);
    model.component("comp2").physics("dode").field("dimensionless").field("por");
    model.component("comp2").physics("dode").field("dimensionless").component(1, "por");
    model.component("comp2").physics("dode").feature("dode1").setIndex("f", "-por*chem.r_1*chem.M_C/rho_soot", 0);
    model.component("comp2").physics("dode").feature("init1").set("por", "por0");
    model.component("comp2").physics("chem").feature("CH4").set("GasThermalConductivitySel", "UserDefined");
    model.component("comp2").physics("chem").feature("CH4").set("kgas", "kt_CH4");
    model.component("comp2").physics("chem").feature("CH4").set("speciesEnthalpy", "UserDefined");
    model.component("comp2").physics("chem").feature("CH4").set("Cp", "Cp_CH4*chem.M_CH4");
    model.component("comp2").physics("chem").feature("CH4").set("h", "h_CH4");
    model.component("comp2").physics("chem").feature("CH4").set("s", "s_CH4");
    model.component("comp2").physics("chem").feature("H2").set("GasThermalConductivitySel", "UserDefined");
    model.component("comp2").physics("chem").feature("H2").set("kgas", "kt_H2");
    model.component("comp2").physics("chem").feature("H2").set("speciesEnthalpy", "UserDefined");
    model.component("comp2").physics("chem").feature("H2").set("Cp", "Cp_H2*chem.M_H2");
    model.component("comp2").physics("chem").feature("H2").set("h", "h_H2");
    model.component("comp2").physics("chem").feature("H2").set("s", "s_H2");
    model.component("comp2").physics("chem").feature("C").set("GasThermalConductivitySel", "UserDefined");
    model.component("comp2").physics("chem").feature("C").set("kgas", "kt_C");
    model.component("comp2").physics("chem").feature("C").set("speciesEnthalpy", "UserDefined");
    model.component("comp2").physics("chem").feature("C").set("Cp", "Cp_C*chem.M_C");
    model.component("comp2").physics("chem").feature("C").set("h", "h_C");
    model.component("comp2").physics("chem").feature("C").set("s", "s_C");
    model.component("comp2").physics("chem").feature("a").set("M", "M_a");
    model.component("comp2").physics("chem").feature("a").set("GasThermalConductivitySel", "UserDefined");
    model.component("comp2").physics("chem").feature("a").set("kgas", "kt_cat");
    model.component("comp2").physics("chem").feature("a").set("speciesEnthalpy", "UserDefined");
    model.component("comp2").physics("tcs").prop("TransportMechanism").set("MassTransferInPorousMedia", true);
    model.component("comp2").physics("tcs").feature("cdm1").setIndex("DiffusivityFrom", "UserDefined", 0, 0);
    model.component("comp2").physics("tcs").feature("cdm1").setIndex("DF", "D_CH4H2", 0, 0);
    model.component("comp2").physics("tcs").feature("reac1").selection().set(2);
    model.component("comp2").physics("tcs").feature("reac1").set("ReactingVolumeType", "PoreVolume");
    model.component("comp2").physics("tcs").create("in1", "Inflow", 1);
    model.component("comp2").physics("tcs").feature("in1").selection().set(2);
    model.component("comp2").physics("tcs").feature("in1").setIndex("wbnd", 0, 1);
    model.component("comp2").physics("tcs").create("out1", "Outflow", 1);
    model.component("comp2").physics("tcs").feature("out1").selection().set(9);
    model.component("comp2").physics("tcs").create("porous1", "PorousMedium", 2);
    model.component("comp2").physics("tcs").feature("porous1").selection().set(2);
    model.component("comp2").physics("tcs").feature("porous1").feature("fluid1")
         .set("minput_pressure_src", "root.comp2.spf.pA");
    model.component("comp2").physics("tcs").feature("porous1").feature("fluid1").set("u_src", "root.comp2.u");
    model.component("comp2").physics("tcs").feature("porous1").feature("fluid1").setIndex("DF", "D_CH4H2", 0, 0);
    model.component("comp2").physics("tcs").feature("porous1").feature("pm1").set("epsilon_p", "por");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("molarmass_mat", "root.comp2.chem.Mn");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1").set("poro", "por");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1").set("k_sp_mat", "userdef");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1")
         .set("k_sp", new String[]{"kt_cat", "0", "0", "0", "kt_cat", "0", "0", "0", "kt_cat"});
    model.component("comp2").physics("ht").feature("porous1").feature("pm1").set("rho_sp_mat", "userdef");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1").set("rho_sp", "rho_cat");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1").set("Cp_sp_mat", "userdef");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1").set("Cp_sp", "Cp_cat");
    model.component("comp2").physics("ht").feature("hs1").selection().set(2);
    model.component("comp2").physics("ht").feature("hs1").set("Q0", "chem.Qtot*por");
    model.component("comp2").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp2").physics("ht").feature("temp1").selection().set(2);
    model.component("comp2").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp2").physics("ht").feature("ofl1").selection().set(9);
    model.component("comp2").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp2").physics("ht").feature("temp2").selection().set(13);
    model.component("comp2").physics("ht").feature("temp2").set("T0", "850[K]");
    model.component("comp2").physics("ht").create("fluid1", "FluidHeatTransferModel", 2);
    model.component("comp2").physics("ht").feature("fluid1").selection().set(1, 3);
    model.component("comp2").physics("spf").prop("PhysicalModelProperty").set("EnablePorousMediaDomains", true);
    model.component("comp2").physics("spf").create("porous1", "PorousMedium", 2);
    model.component("comp2").physics("spf").feature("porous1").selection().set(2);
    model.component("comp2").physics("spf").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp2").physics("spf").feature("porous1").feature("pm1").set("epsilon_p", "por");
    model.component("comp2").physics("spf").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp2").physics("spf").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa", "0", "0", "0", "kappa", "0", "0", "0", "kappa"});
    model.component("comp2").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp2").physics("spf").feature("inl1").selection().set(2);
    model.component("comp2").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp2").physics("spf").feature("inl1").set("Uavfdf", "u_in");
    model.component("comp2").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp2").physics("spf").feature("out1").selection().set(9);
    model.component("comp2").physics("spf").create("ms1", "MassSource", 2);
    model.component("comp2").physics("spf").feature("ms1").selection().set(2);
    model.component("comp2").physics("spf").feature("ms1").set("Qm", "(chem.Rw_CH4+chem.Rw_H2)*por");

    model.study("std2").feature("stat").setSolveFor("/physics/chem", false);
    model.study("std2").feature("stat").setSolveFor("/physics/tcs", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/physics/dode", false);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("tlist", "range(0,50,1000) range(2000,1000,20000)");
    model.study("std2").feature("time").setSolveFor("/physics/re", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 40, 0);
    model.result("pg3").label("\u6d53\u5ea6, CH4 (tcs)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"tcs.c_wCH4"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"tcs.tflux_wCH4r", "tcs.tflux_wCH4z"});
    model.result("pg3").feature("arws1").set("xnumber", 10);
    model.result("pg3").feature("arws1").set("ynumber", 10);
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").feature("arws1").create("sel1", "Selection");
    model.result("pg3").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset4");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 40, 0);
    model.result("pg4").label("\u6d53\u5ea6, CH4, 3D (tcs)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"tcs.c_wCH4"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 40, 0);
    model.result("pg5").label("\u6d53\u5ea6, H2 (tcs)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"tcs.c_wH2"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"tcs.tflux_wH2r", "tcs.tflux_wH2z"});
    model.result("pg5").feature("arws1").set("xnumber", 10);
    model.result("pg5").feature("arws1").set("ynumber", 10);
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").feature("arws1").create("sel1", "Selection");
    model.result("pg5").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "rev1");
    model.result("pg6").setIndex("looplevel", 40, 0);
    model.result("pg6").label("\u6d53\u5ea6, H2, 3D (tcs)");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"tcs.c_wH2"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result().dataset("dset4").set("geom", "geom1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u901f\u5ea6 (spf)");
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "spf.U");
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u538b\u529b (spf)");
    model.result("pg8").set("dataisaxisym", "off");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("con1", "Contour");
    model.result("pg8").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("expr", "p");
    model.result("pg8").feature("con1").set("number", 40);
    model.result("pg8").feature("con1").set("levelrounding", false);
    model.result("pg8").feature("con1").set("colortable", "Rainbow");
    model.result("pg8").feature("con1").set("smooth", "internal");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset4");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("\u8868\u9762");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("expr", "spf.U");
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").feature("surf1").set("smooth", "internal");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u6e29\u5ea6 (ht)");
    model.result("pg10").set("dataisaxisym", "off");
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "T");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").setIndex("looplevel", 40, 0);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").label("\u5b54\u9699\u7387\u53d8\u5316");
    model.result("pg11").feature("surf1").set("expr", "por");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "rev1");
    model.result("pg12").setIndex("looplevel", 40, 0);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").label("\u5b54\u9699\u7387\u53d8\u5316 1");
    model.result("pg12").feature("surf1").set("expr", "por");
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result().remove("pg6");
    model.result("pg7").run();
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").set("data", "dset4");
    model.result("pg13").setIndex("looplevel", 40, 0);
    model.result("pg13").set("titletype", "manual");
    model.result("pg13")
         .set("title", "\u8868\u9762: \u6d53\u5ea6 (mol/m<sup>3</sup>) \u9762\u7bad\u5934: \u603b\u901a\u91cf");
    model.result("pg13").set("showlegendsunit", true);
    model.result("pg13").set("showlegendstitle", true);
    model.result("pg13").label("\u7ed8\u56fe\u9635\u5217: \u6d53\u5ea6, CH4, H2 (tcs)");
    model.result("pg13").set("plotarrayenable", true);
    model.result("pg13").set("arrayshape", "linear");
    model.result("pg13").set("legendpos", "rightdouble");
    model.result("pg13").set("arrayaxis", "x");
    model.result("pg13").set("relpadding", 0.5);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", new String[]{"tcs.c_wCH4"});
    model.result("pg13").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg13").feature("surf1").label("\u8868\u9762, CH4");
    model.result("pg13").feature("surf1").set("legendtitle", "CH4");
    model.result("pg13").feature("surf1").set("manualindexing", true);
    model.result("pg13").feature("surf1").set("arrayindex", 0);
    model.result("pg13").create("arws1", "ArrowSurface");
    model.result("pg13").feature("arws1").set("expr", new String[]{"tcs.tflux_wCH4r", "tcs.tflux_wCH4z"});
    model.result("pg13").feature("arws1").set("xnumber", 7);
    model.result("pg13").feature("arws1").set("ynumber", 7);
    model.result("pg13").feature("arws1").set("color", "black");
    model.result("pg13").feature("arws1").create("sel1", "Selection");
    model.result("pg13").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result("pg13").feature("arws1").label("\u603b\u901a\u91cf, CH4");
    model.result("pg13").feature("arws1").set("manualindexing", true);
    model.result("pg13").feature("arws1").set("arrayindex", 0);
    model.result("pg13").create("ann1", "Annotation");
    model.result("pg13").feature("ann1").set("showpoint", false);
    model.result("pg13").feature("ann1").set("text", "CH4");
    model.result("pg13").feature("ann1").label("CH4");
    model.result("pg13").feature("ann1").set("posxexpr", 0);
    model.result("pg13").feature("ann1").set("posyexpr", 0);
    model.result("pg13").feature("ann1").set("manualindexing", true);
    model.result("pg13").feature("ann1").set("arrayindex", 0);
    model.result("pg13").create("surf2", "Surface");
    model.result("pg13").feature("surf2").set("expr", new String[]{"tcs.c_wH2"});
    model.result("pg13").feature("surf2").set("colortable", "Baptisia");
    model.result("pg13").feature("surf2").label("\u8868\u9762, H2");
    model.result("pg13").feature("surf2").set("legendtitle", "H2");
    model.result("pg13").feature("surf2").set("manualindexing", true);
    model.result("pg13").feature("surf2").set("arrayindex", 1);
    model.result("pg13").create("arws2", "ArrowSurface");
    model.result("pg13").feature("arws2").set("expr", new String[]{"tcs.tflux_wH2r", "tcs.tflux_wH2z"});
    model.result("pg13").feature("arws2").set("xnumber", 7);
    model.result("pg13").feature("arws2").set("ynumber", 7);
    model.result("pg13").feature("arws2").set("color", "black");
    model.result("pg13").feature("arws2").create("sel1", "Selection");
    model.result("pg13").feature("arws2").feature("sel1").selection().set(1, 2, 3);
    model.result("pg13").feature("arws2").label("\u603b\u901a\u91cf, H2");
    model.result("pg13").feature("arws2").set("manualindexing", true);
    model.result("pg13").feature("arws2").set("arrayindex", 1);
    model.result("pg13").create("ann2", "Annotation");
    model.result("pg13").feature("ann2").set("showpoint", false);
    model.result("pg13").feature("ann2").set("text", "H2");
    model.result("pg13").feature("ann2").label("H2");
    model.result("pg13").feature("ann2").set("posxexpr", 0);
    model.result("pg13").feature("ann2").set("posyexpr", 0);
    model.result("pg13").feature("ann2").set("manualindexing", true);
    model.result("pg13").feature("ann2").set("arrayindex", 1);
    model.result("pg13").label("\u7ed8\u56fe\u9635\u5217: \u6d53\u5ea6, CH4, H2 (tcs)");
    model.result("pg13").run();
    model.result("pg13").setIndex("looplevel", 2, 0);
    model.result("pg13").set("titletype", "none");
    model.result("pg13").set("relpadding", 1);
    model.result("pg13").feature("ann1").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("ann1").set("text", "50s");
    model.result("pg13").feature("surf2").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("surf2").set("colortable", "Iodinea");
    model.result("pg13").feature("ann2").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("ann2").set("text", "50s");
    model.result("pg13").feature("surf1").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("surf3", "surf1");
    model.result("pg13").feature().duplicate("arws3", "arws1");
    model.result("pg13").feature().duplicate("ann3", "ann1");
    model.result("pg13").feature().duplicate("surf4", "surf2");
    model.result("pg13").feature().duplicate("arws4", "arws2");
    model.result("pg13").feature().duplicate("ann4", "ann2");
    model.result("pg13").feature("surf3").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("surf3").set("data", "dset4");
    model.result("pg13").feature("surf3").setIndex("looplevel", 11, 0);
    model.result("pg13").feature("surf3").set("inheritplot", "surf1");
    model.result("pg13").feature("surf3").set("arrayindex", 2);
    model.result("pg13").feature("arws3").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("arws3").set("data", "dset4");
    model.result("pg13").feature("arws3").setIndex("looplevel", 11, 0);
    model.result("pg13").feature("arws3").set("arrayindex", 2);
    model.result("pg13").feature("ann3").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("ann3").set("text", "500s");
    model.result("pg13").feature("ann3").set("arrayindex", 2);
    model.result("pg13").feature("surf4").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("surf4").set("data", "dset4");
    model.result("pg13").feature("surf4").setIndex("looplevel", 11, 0);
    model.result("pg13").feature("surf4").set("arrayindex", 3);
    model.result("pg13").feature("surf4").set("inheritplot", "surf2");
    model.result("pg13").feature("arws4").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("arws4").set("data", "dset4");
    model.result("pg13").feature("arws4").setIndex("looplevel", 11, 0);
    model.result("pg13").feature("arws4").set("arrayindex", 3);
    model.result("pg13").feature("ann4").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("ann4").set("text", "500s");
    model.result("pg13").feature("ann4").set("arrayindex", 3);
    model.result("pg13").run();
    model.result("pg10").run();
    model.result("pg10").setIndex("looplevel", 2, 0);
    model.result("pg10").set("titletype", "none");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").set("plotarrayenable", true);
    model.result("pg10").set("relpadding", 1.5);
    model.result("pg13").feature("ann1").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg10").run();
    model.result("pg10").feature().copy("ann1", "pg13/ann1");
    model.result("pg10").feature("ann1").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("ann1").label("50s");
    model.result("pg10").feature("surf1").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("surf2", "surf1");
    model.result("pg10").feature().duplicate("ann2", "ann1");
    model.result("pg10").feature("surf2").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("surf2").set("data", "dset4");
    model.result("pg10").feature("surf2").set("solutionparams", "manual");
    model.result("pg10").feature("surf2").setIndex("looplevel", 11, 0);
    model.result("pg10").feature("surf2").set("inheritplot", "surf1");
    model.result("pg10").feature("ann2").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("ann2").set("arrayindex", 1);
    model.result("pg10").feature("ann2").label("500s");
    model.result("pg10").feature("ann2").set("text", "500s");
    model.result("pg10").feature("ann2").set("inheritplot", "surf1");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg9").run();
    model.result("pg9").set("titletype", "none");
    model.result("pg9").label("\u901f\u5ea6\u548c\u538b\u529b\uff0c\u4e09\u7ef4 (spf)");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").set("legendpos", "bottom");
    model.result("pg9").set("edges", false);
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("colortable", "Acanthaster");
    model.result("pg9").run();
    model.result("pg9").create("con1", "Contour");
    model.result("pg9").feature("con1").set("expr", "p");
    model.result("pg9").feature("con1").set("contourtype", "tubes");
    model.result("pg9").feature("con1").set("colortable", "Bryophyta");
    model.result("pg9").run();
    model.result("pg9").set("view", "view2");
    model.result("pg9").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", 0.044, 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.156, 1, 2);
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("CH4 \u6d53\u5ea6\uff0c\u591a\u5b54\u50ac\u5316\u5242\u5e8a\u4e2d\u5fc3");
    model.result("pg14").set("data", "cln1");
    model.result("pg14").setIndex("looplevelinput", "manual", 0);
    model.result("pg14").setIndex("looplevel", new int[]{2, 11}, 0);
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("xlabelactive", true);
    model.result("pg14").set("xlabel", "\u591a\u5b54\u50ac\u5316\u5e8a\u957f\u5ea6 (m)");
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg14").set("legendpos", "middleleft");
    model.result("pg14").create("lngr1", "LineGraph");
    model.result("pg14").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg14").feature("lngr1").set("linewidth", "preference");
    model.result("pg14").feature("lngr1").set("expr", "tcs.c_wCH4");
    model.result("pg14").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6");
    model.result("pg14").feature("lngr1").set("linewidth", 2);
    model.result("pg14").feature("lngr1").set("legend", true);
    model.result("pg14").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg14").feature("lngr1").set("legendpattern", "CH<sub>4</sub> eval(t,s) s");
    model.result("pg14").feature().duplicate("lngr2", "lngr1");
    model.result("pg14").run();
    model.result("pg14").feature("lngr2").set("expr", "tcs.c_wH2");
    model.result("pg14").feature("lngr2").set("descr", "\u6469\u5c14\u6d53\u5ea6");
    model.result("pg14").feature("lngr2").set("linestyle", "dashed");
    model.result("pg14").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg14").feature("lngr2").set("legendpattern", "H<sub>2</sub> eval(t,s) s");
    model.result("pg14").run();
    model.result("pg12").run();
    model.result("pg12").label("\u5b54\u9699\u7387\u5206\u5e03");
    model.result("pg12").set("titletype", "none");
    model.result("pg12").set("view", "view2");
    model.result("pg12").set("legendpos", "bottom");
    model.result("pg12").run();
    model.result("pg12").feature("surf1").set("colortable", "Agama");
    model.result("pg12").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg12").run();

    model.study("std1").feature("time").setSolveFor("/physics/dode", false);

    model.result("pg11").run();
    model.result().remove("pg11");
    model.result("pg12").run();
    model.result().duplicate("pg15", "pg12");
    model.result("pg15").run();
    model.result("pg15").label("\u53cd\u5e94\u5668\u6982\u89c8");
    model.result("pg15").set("edges", false);
    model.result("pg15").set("showlegends", false);
    model.result("pg15").run();
    model.result("pg15").feature("surf1").set("colortabletype", "discrete");
    model.result("pg15").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg15").feature("surf1").feature("mtrl1").set("family", "rock");
    model.result("pg15").feature("surf1").feature("mtrl1").set("useplotcolors", true);
    model.result().dataset().duplicate("rev2", "rev1");
    model.result().dataset("rev2").selection().geom("geom1", 1);
    model.result().dataset("rev2").selection().geom("geom1", 1);
    model.result().dataset("rev2").selection().set(10, 11, 12, 13, 14, 15, 16, 17, 18);
    model.result("pg15").run();
    model.result("pg15").feature().duplicate("surf2", "surf1");
    model.result("pg15").run();
    model.result("pg15").feature("surf2").set("data", "rev2");
    model.result("pg15").feature("surf2").set("expr", "1");
    model.result("pg15").run();
    model.result("pg15").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg15").feature("surf2").feature("mtrl1").set("useplotcolors", false);
    model.result("pg15").run();
    model.result("pg15").feature("surf2").create("sel1", "Selection");
    model.result("pg15").feature("surf2").feature("sel1").selection().set(1, 2, 3);
    model.result("pg15").feature("surf2").feature("sel1").set("evalstartcap", false);
    model.result("pg15").feature("surf2").feature("sel1").set("evalendcap", false);

    model.view().create("view3", 3);

    model.result("pg15").run();
    model.result("pg15").set("view", "view3");
    model.result("pg15").run();

    model.view("view3").camera().set("orthoscale", 0.19918322563171387);
    model.view("view3").camera().set("zoomanglefull", 60.963966369628906);
    model.view("view3").camera()
         .set("position", new double[]{-0.05673779919743538, -0.07030794769525528, -0.0421682670712471});
    model.view("view3").camera()
         .set("target", new double[]{0.31598028540611267, 0.3743983507156372, 0.8570875525474548});
    model.view("view3").camera()
         .set("up", new double[]{-0.9251288175582886, 0.00781339406967163, 0.3795729875564575});
    model.view("view3").camera()
         .set("rotationpoint", new double[]{0.002187073230743408, -2.4065375328063965E-6, 0.10000000149011612});
    model.view("view3").camera().set("viewoffset", new double[]{0.04507114738225937, 0.2020272384450007});
    model.view("view3").light().create("lgt4", "HeadLight");
    model.view("view3").light("lgt4").set("intensity", 0.39);
    model.view("view3").light("lgt4").set("specular", 0.3);
    model.view("view3").set("showgrid", false);
    model.view("view3").set("showaxisorientation", false);
    model.view("view3").set("ssao", true);
    model.view("view3").set("flooreffect", true);
    model.view("view3").set("locked", true);

    model.result("pg7").run();
    model.result().remove("pg7");
    model.result().remove("pg8");
    model.result("pg9").run();
    model.result("pg15").run();

    model.title("\u591a\u76f8\u50ac\u5316\u4e2d\u7684\u78b3\u6c89\u79ef");

    model
         .description("\u672c\u4f8b\u63a2\u8ba8\u4e86\u7532\u70f7\u5728\u56fa\u4f53 Ni-Al\u2082O\u2083 \u50ac\u5316\u5242\u4f5c\u7528\u4e0b\u7684\u70ed\u5206\u89e3\u53cd\u5e94\uff0c\u6b64\u8fc7\u7a0b\u4f1a\u4ea7\u751f\u6c22\u6c14\u548c\u56fa\u4f53\u78b3\u3002\u78b3\u6c89\u79ef\u4e0d\u4ec5\u4f1a\u6291\u5236\u50ac\u5316\u5242\u7684\u6d3b\u6027\uff0c\u8fd8\u4f1a\u964d\u4f4e\u53cd\u5e94\u5668\u7684\u6e17\u900f\u6027\u3002\u672c\u4f8b\u901a\u8fc7\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u5728\u5b8c\u5168\u6df7\u5408\u7684\u73af\u5883\u4e2d\uff0c\u7ed3\u5408\u7a7a\u95f4\u76f8\u5173\u7684\u6a21\u578b\u5bf9\u8fd9\u4e00\u8fc7\u7a0b\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("carbon_deposition.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
