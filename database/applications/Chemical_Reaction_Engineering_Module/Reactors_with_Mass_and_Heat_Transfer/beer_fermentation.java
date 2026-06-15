/*
 * beer_fermentation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:21 by COMSOL 6.3.0.290. */
public class beer_fermentation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T0", "12[degC]", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("Tc", "12[degC]", "\u51b7\u5374\u4ecb\u8d28\u6e29\u5ea6");
    model.param().set("qv", "8[W/(m^3*K)]", "\u51b7\u5374\u901f\u7387\u80fd\u529b");
    model.param().set("EG", "22.6[kcal/mol]", "\u6d3b\u5316\u80fd\uff0c\u8461\u8404\u7cd6\u6d88\u8017\u91cf");
    model.param().set("EM", "11.3[kcal/mol]", "\u6d3b\u5316\u80fd\uff0c\u9ea6\u82bd\u7cd6\u6d88\u8017\u91cf");
    model.param().set("EN", "7.16[kcal/mol]", "\u6d3b\u5316\u80fd\uff0c\u9ea6\u82bd\u4e09\u7cd6\u6d88\u8017\u91cf");
    model.param()
         .set("EHG1", "-68.6[kcal/mol]", "\u6d3b\u5316\u80fd\uff0c\u8461\u8404\u7cd6\u6291\u5236\u673a\u5236 1");
    model.param()
         .set("EHM1", "-14.4[kcal/mol]", "\u6d3b\u5316\u80fd\uff0c\u9ea6\u82bd\u7cd6\u6291\u5236\u673a\u5236 1");
    model.param()
         .set("EHN1", "-19.9[kcal/mol]", "\u6d3b\u5316\u80fd\uff0c\u9ea6\u82bd\u4e09\u7cd6\u6291\u5236\u673a\u5236 1");
    model.param()
         .set("EHG2", "10.2[kcal/mol]", "\u6d3b\u5316\u80fd\uff0c\u8461\u8404\u7cd6\u6291\u5236\u673a\u5236 2");
    model.param()
         .set("EHM2", "26.3[kcal/mol]", "\u6d3b\u5316\u80fd\uff0c\u9ea6\u82bd\u7cd6\u6291\u5236\u673a\u5236 2");
    model.param().set("AG", "exp(35.77)[1/h]", "\u9891\u7387\u56e0\u5b50\uff0c\u8461\u8404\u7cd6\u6d88\u8017\u91cf");
    model.param().set("AM", "exp(16.4)[1/h]", "\u9891\u7387\u56e0\u5b50\uff0c\u9ea6\u82bd\u7cd6\u6d88\u8017\u91cf");
    model.param()
         .set("AN", "exp(10.59)[1/h]", "\u9891\u7387\u56e0\u5b50\uff0c\u9ea6\u82bd\u4e09\u7cd6\u6d88\u8017\u91cf");
    model.param()
         .set("AHG1", "exp(-121.3)[mol/m^3]", "\u9891\u7387\u56e0\u5b50\uff0c\u8461\u8404\u7cd6\u6291\u5236\u673a\u5236 1");
    model.param()
         .set("AHM1", "exp(-19.5)[mol/m^3]", "\u9891\u7387\u56e0\u5b50\uff0c\u9ea6\u82bd\u7cd6\u6291\u5236\u673a\u5236 1");
    model.param()
         .set("AHN1", "exp(-26.78)[mol/m^3]", "\u9891\u7387\u56e0\u5b50\uff0c\u9ea6\u82bd\u4e09\u7cd6\u6291\u5236\u673a\u5236 1");
    model.param()
         .set("AHG2", "exp(23.33)[mol/m^3]", "\u9891\u7387\u56e0\u5b50\uff0c\u8461\u8404\u7cd6\u6291\u5236\u673a\u5236 2");
    model.param()
         .set("AHM2", "exp(55.61)[mol/m^3]", "\u9891\u7387\u56e0\u5b50\uff0c\u9ea6\u82bd\u7cd6\u6291\u5236\u673a\u5236 2");
    model.param().set("HG", "-91.2[kJ/mol]", "\u53d1\u9175\u70ed\uff0c\u8461\u8404\u7cd6");
    model.param().set("HM", "-226.3[kJ/mol]", "\u53d1\u9175\u70ed\uff0c\u9ea6\u82bd\u7cd6");
    model.param().set("HN", "-361.3[kJ/mol]", "\u53d1\u9175\u70ed\uff0c\u9ea6\u82bd\u4e09\u7cd6");
    model.param()
         .set("KX", "365000[mol^2/m^6]", "\u7ecf\u9a8c\u6027\u9175\u6bcd\u751f\u957f\u6291\u5236\u5e38\u6570");
    model.param()
         .set("YXG", "0.134", "\u9175\u6bcd\u4ea7\u7387\u7cfb\u6570\uff0c\u8461\u8404\u7cd6\u6d88\u8017\u91cf");
    model.param()
         .set("YXM", "0.268", "\u9175\u6bcd\u4ea7\u7387\u7cfb\u6570\uff0c\u9ea6\u82bd\u7cd6\u6d88\u8017\u91cf");
    model.param()
         .set("YXN", "0.402", "\u9175\u6bcd\u4ea7\u7387\u7cfb\u6570\uff0c\u9ea6\u82bd\u4e09\u7cd6\u6d88\u8017\u91cf");
    model.param()
         .set("YEG", "1.92", "\u4e59\u9187\u4ea7\u7387\u7cfb\u6570\uff0c\u8461\u8404\u7cd6\u6d88\u8017\u91cf");
    model.param()
         .set("YEM", "3.84", "\u4e59\u9187\u4ea7\u7387\u7cfb\u6570\uff0c\u9ea6\u82bd\u7cd6\u6d88\u8017\u91cf");
    model.param()
         .set("YEN", "5.76", "\u4e59\u9187\u4ea7\u7387\u7cfb\u6570\uff0c\u9ea6\u82bd\u4e09\u7cd6\u6d88\u8017\u91cf");
    model.param().set("YCG", "1.97", "CO2 \u4ea7\u7387\u7cfb\u6570\uff0c\u8461\u8404\u7cd6\u6d88\u8017\u91cf");
    model.param().set("YCM", "3.94", "CO2 \u4ea7\u7387\u7cfb\u6570\uff0c\u9ea6\u82bd\u7cd6\u6d88\u8017\u91cf");
    model.param().set("YCN", "5.91", "CO2 \u4ea7\u7387\u7cfb\u6570\uff0c\u9ea6\u82bd\u4e09\u7cd6\u6d88\u8017\u91cf");
    model.param()
         .set("YEtAc", "0.000992", "\u4e59\u9178\u4e59\u916f\u4ea7\u7387\u7cfb\u6570\uff0c\u7cd6\u6d88\u8017\u91cf");
    model.param().set("YAcA", "0.01", "\u4e59\u919b\u4ea7\u7387\u7cfb\u6570\uff0c\u7cd6\u6d88\u8017\u91cf");
    model.param().set("AAcA", "exp(10.4)[m^3/(h*mol)]", "\u9891\u7387\u56e0\u5b50\uff0c\u4e59\u919b\u5438\u6536");
    model.param().set("EAcA", "11.1[kcal/mol]", "\u6d3b\u5316\u80fd\uff0c\u4e59\u919b\u5438\u6536");
    model.param().set("rhoH2O", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6c34");
    model.param().set("rhoE", "800[kg/m^3]", "\u5bc6\u5ea6\uff0c\u4e59\u9187");
    model.param().set("CpH2O", "75.42[J/(mol*K)]", "\u6469\u5c14\u70ed\u5bb9\uff0c\u6c34");
    model.param().set("MG", "0.180[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u8461\u8404\u7cd6");
    model.param().set("MM", "0.342[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u9ea6\u82bd\u7cd6");
    model.param().set("MN", "0.504[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u9ea6\u82bd\u4e09\u7cd6");
    model.param().set("MX", "2e5[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u9175\u6bcd");
    model.param()
         .set("Csat_CO2", "39[mol/m^3]", "1 \u4e2a\u5927\u6c14\u538b CO2 \u5728\u6c34\u4e2d\u7684\u6700\u5927\u6eb6\u89e3\u5ea6\u6d53\u5ea6");
    model.param().set("c0E", "0", "\u521d\u59cb\u6d53\u5ea6\uff0c\u9152\u7cbe\uff08\u4e59\u9187\uff09");
    model.param().set("c0G", "70[mol/m^3]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u8461\u8404\u7cd6");
    model.param().set("c0M", "220[mol/m^3]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u9ea6\u82bd\u7cd6");
    model.param().set("c0N", "40[mol/m^3]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u9ea6\u82bd\u4e09\u7cd6");
    model.param().set("c0CO2", "0[mol/m^3]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u4e8c\u6c27\u5316\u78b3");
    model.param().set("c0X", "30[mol/m^3]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u9175\u6bcd");
    model.param()
         .set("hCO2", "0.07[h^-1]", "\u6c14-\u6db2\u8d28\u91cf\u4f20\u9012\u7cfb\u6570\uff0c\u4e8c\u6c27\u5316\u78b3");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("Temp", "re.T", "\u53cd\u5e94\u91dc\u7684\u6e29\u5ea6");
    model.component("comp1").variable("var1").set("c_G", "re.c_G", "\u8461\u8404\u7cd6\u6d53\u5ea6");
    model.component("comp1").variable("var1").set("c_M", "re.c_M", "\u9ea6\u82bd\u7cd6\u6d53\u5ea6");
    model.component("comp1").variable("var1").set("c_N", "re.c_N", "\u9ea6\u82bd\u4e09\u7cd6\u6d53\u5ea6");
    model.component("comp1").variable("var1")
         .set("kf1", "kfG_max*c_G/(KG1+c_G)", "\u6bd4\u589e\u957f\u7387\uff0c\u8461\u8404\u7cd6");
    model.component("comp1").variable("var1")
         .set("kf2", "kfM_max*c_M/(KM1+c_M)*KG2/(KG2+c_G)", "\u6bd4\u589e\u957f\u7387\uff0c\u9ea6\u82bd\u7cd6");
    model.component("comp1").variable("var1")
         .set("kf3", "kfN_max*c_N/(KN1+c_N)*KG2/(KG2+c_G)*KM2/(KM2+c_M)", "\u6bd4\u589e\u957f\u7387\uff0c\u9ea6\u82bd\u4e09\u7cd6");
    model.component("comp1").variable("var1")
         .set("kfG_max", "AG*exp(-EG/(R_const*Temp))", "\u6700\u5927\u901f\u5ea6\uff0c\u8461\u8404\u7cd6\u6d88\u8017\u91cf");
    model.component("comp1").variable("var1")
         .set("kfM_max", "AM*exp(-EM/(R_const*Temp))", "\u6700\u5927\u901f\u5ea6\uff0c\u9ea6\u82bd\u7cd6\u6d88\u8017\u91cf");
    model.component("comp1").variable("var1")
         .set("kfN_max", "AN*exp(-EN/(R_const*Temp))", "\u6700\u5927\u901f\u5ea6\uff0c\u9ea6\u82bd\u4e09\u7cd6\u6d88\u8017\u91cf");
    model.component("comp1").variable("var1")
         .set("KG1", "AHG1*exp(-EHG1/(R_const*Temp))", "\u7c73\u6c0f\u5e38\u6570\uff0c\u8461\u8404\u7cd6\u6291\u5236\u53cd\u5e94 1");
    model.component("comp1").variable("var1")
         .set("KG2", "AHG2*exp(-EHG2/(R_const*Temp))", "\u7c73\u6c0f\u5e38\u6570\uff0c\u8461\u8404\u7cd6\u6291\u5236\u53cd\u5e94 2");
    model.component("comp1").variable("var1")
         .set("KM1", "AHM1*exp(-EHM1/(R_const*Temp))", "\u7c73\u6c0f\u5e38\u6570\uff0c\u9ea6\u82bd\u7cd6\u6291\u5236\u53cd\u5e94 1");
    model.component("comp1").variable("var1")
         .set("KN1", "AHN1*exp(-EHN1/(R_const*Temp))", "\u7c73\u6c0f\u5e38\u6570\uff0c\u9ea6\u82bd\u4e09\u7cd6\u6291\u5236\u53cd\u5e94 1");
    model.component("comp1").variable("var1")
         .set("KM2", "AHM2*exp(-EHM2/(R_const*Temp))", "\u7c73\u6c0f\u5e38\u6570\uff0c\u9ea6\u82bd\u7cd6\u6291\u5236\u53cd\u5e94 2");
    model.component("comp1").variable("var1").set("Evol", "re.c_E*re.M_E/rhoE*100", "\u9152\u7cbe\u542b\u91cf");
    model.component("comp1").variable("var1")
         .set("kAcA", "AAcA*exp(-EAcA/(R_const*Temp))", "\u901f\u7387\u5e38\u6570\uff0c\u4e59\u919b\u5438\u6536");

    model.component("comp1").physics("re").prop("energybalance").set("energybalance", "include");
    model.component("comp1").physics("re").prop("energybalance").set("Qext", "-qv*(re.T-Tc)*re.Vr");
    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "X");
    model.component("comp1").physics("re").feature("X").set("SpeciesrateSelection", "UserDefined");
    model.component("comp1").physics("re").feature("X")
         .set("Speciesrate", "(YXG*kf1+YXM*kf2+YXN*kf3)*re.c_X*KX/(KX+(re.c_X-c0X)^2)");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "G=>E+CO2+EtAc+AcA");
    model.component("comp1").physics("re").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch1").set("r", "kf1*re.c_X");
    model.component("comp1").physics("re").feature("rch1").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("re").feature("rch1").set("H", "HG");
    model.component("comp1").physics("re").feature("E").set("enableChemicalFormulaCheckbox", true);
    model.component("comp1").physics("re").feature("E").set("chemicalFormula", "C2H5OH");
    model.component("comp1").physics("re").feature("E").set("SpeciesrateSelection", "UserDefined");
    model.component("comp1").physics("re").feature("E").set("Speciesrate", "(YEG*kf1+YEM*kf2+YEN*kf3)*re.c_X");
    model.component("comp1").physics("re").feature("CO2").set("SpeciesrateSelection", "UserDefined");
    model.component("comp1").physics("re").feature("CO2").set("Speciesrate", "hCO2*(Csat_CO2-re.c_CO2)");
    model.component("comp1").physics("re").feature("EtAc").set("enableChemicalFormulaCheckbox", true);
    model.component("comp1").physics("re").feature("EtAc").set("chemicalFormula", "C4H8O2");
    model.component("comp1").physics("re").feature("EtAc").set("SpeciesrateSelection", "UserDefined");
    model.component("comp1").physics("re").feature("EtAc").set("Speciesrate", "YEtAc*(kf1+kf2+kf3)*re.c_X");
    model.component("comp1").physics("re").feature("AcA").set("enableChemicalFormulaCheckbox", true);
    model.component("comp1").physics("re").feature("AcA").set("chemicalFormula", "C2H4O");
    model.component("comp1").physics("re").feature("AcA").set("SpeciesrateSelection", "UserDefined");
    model.component("comp1").physics("re").feature("AcA")
         .set("Speciesrate", "YAcA*(kf1+kf2+kf3)*re.c_X-kAcA*re.c_AcA*re.c_X");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "M=>E+CO2+EtAc+AcA");
    model.component("comp1").physics("re").feature("rch2").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch2").set("r", "kf2*re.c_X");
    model.component("comp1").physics("re").feature("rch2").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("re").feature("rch2").set("H", "HM");
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "N=>E+CO2+EtAc+AcA");
    model.component("comp1").physics("re").feature("rch3").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch3").set("r", "kf3*re.c_X");
    model.component("comp1").physics("re").feature("rch3").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("re").feature("rch3").set("H", "HN");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("N").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("re").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("re").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("re").feature("H2O").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("re").feature("H2O").set("Cp", "CpH2O");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "CO2(g)");
    model.component("comp1").physics("re").feature("CO2_gas").set("SpeciesrateSelection", "UserDefined");
    model.component("comp1").physics("re").feature("CO2_gas")
         .set("Speciesrate", "max((YXG*kf1+YXM*kf2+YXN*kf3)*re.c_X-hCO2*(Csat_CO2-re.c_CO2),eps)");
    model.component("comp1").physics("re").feature("inits1").set("T0", "T0");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c0E", 3, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c0G", 5, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "rhoH2O/re.M_H2O", 6, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c0M", 7, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c0N", 8, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c0X", 9, 0);

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,1,400)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-6");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").set("atolglobal", 1.0E-7);
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_X", "re.c_G", "re.c_E", "re.c_CO2", "re.c_EtAc", "re.c_AcA", "re.c_M", "re.c_N", "re.c_CO2_gas"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.T"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result("pg2").label("\u6e29\u5ea6 (re)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("\u7cd6");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_G"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_G", "re.c_M"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_G", "re.c_M", "re.c_N"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("titletype", "manual");
    model.result("pg1").feature("glob1").set("title", "\u7cd6\u6d53\u5ea6");
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u8461\u8404\u7cd6", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "\u9ea6\u82bd\u7cd6", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "\u9ea6\u82bd\u4e09\u7cd6", 2);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u9152\u7cbe");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u9152\u7cbe\u542b\u91cf");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("expr", new String[]{"Evol"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u9152\u7cbe\u542b\u91cf"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg3").feature("glob1").set("title", "\u9152\u7cbe\u542b\u91cf");
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u9175\u6bcd");
    model.result("pg4").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("expr", new String[]{"re.c_X"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg4").feature("glob1").set("title", "\u9175\u6bcd\u6d53\u5ea6");
    model.result("pg4").run();
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u98ce\u5473\u7269\u8d28");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("expr", new String[]{"re.c_EtAc"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg5").feature("glob1").set("expr", new String[]{"re.c_EtAc", "re.c_AcA"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg5").feature("glob1").set("title", "\u98ce\u5473\u7269\u8d28\u6d53\u5ea6");
    model.result("pg5").feature("glob1").setIndex("legends", "\u916f\uff0c\u7406\u60f3\u7684", 0);
    model.result("pg5").feature("glob1").setIndex("legends", "\u919b\uff0c\u4e0d\u7406\u60f3\u7684", 1);
    model.result("pg5").feature("glob1").setIndex("legends", "", 2);
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u6e29\u5ea6 (<sup>o</sup>C)");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").setIndex("expr", "re.T-273.15[K]", 0);
    model.result("pg2").feature("glob1").set("titletype", "manual");
    model.result("pg2").feature("glob1").set("title", "\u6e29\u5ea6");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").run();

    model.title("\u5564\u9152\u917f\u9020\u4e2d\u7684\u53d1\u9175");

    model
         .description("\u5728\u5564\u9152\u53d1\u9175\u8fc7\u7a0b\u4e2d\uff0c\u9175\u6bcd\u901a\u8fc7\u6d88\u8017\u7cd6\u5206\u5408\u6210\u9152\u7cbe\u3001\u4e8c\u6c27\u5316\u78b3\u548c\u591a\u79cd\u53e3\u5473\u5143\u7d20\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u5bf9\u5168\u6df7\u91dc\u4e2d\u7684\u53d1\u9175\u8fc7\u7a0b\u8fdb\u884c\u5efa\u6a21\u3002\n\n\u672c\u6a21\u578b\u4e0d\u4ec5\u53ef\u7528\u4e8e\u4f18\u5316\u5564\u9152\u53d1\u9175\u8fc7\u7a0b\uff0c\u63d0\u5347\u5564\u9152\u7684\u53e3\u611f\uff0c\u8fd8\u53ef\u4ee5\u7528\u6765\u7814\u7a76\u521d\u59cb\u7cd6\u542b\u91cf\u3001\u6e29\u5ea6\u4ee5\u53ca\u9175\u6bcd\u7c7b\u578b\u7b49\u56e0\u7d20\u5bf9\u53d1\u9175\u8fc7\u7a0b\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("beer_fermentation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
