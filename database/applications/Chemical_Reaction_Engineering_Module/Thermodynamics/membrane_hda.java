/*
 * membrane_hda.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:29 by COMSOL 6.3.0.290. */
public class membrane_hda {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Thermodynamics");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("spf", "StationaryPlugflow");
    model.study("std1").feature("spf").set("solnum", "auto");
    model.study("std1").feature("spf").set("notsolnum", "auto");
    model.study("std1").feature("spf").set("outputmap", new String[]{});
    model.study("std1").feature("spf").setSolveFor("/physics/re", true);

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"hydrogen", "1333-74-0", "H2", "COMSOL"}, 
         {"methane", "74-82-8", "CH4", "COMSOL"}, 
         {"benzene", "71-43-2", "C6H6", "COMSOL"}, 
         {"toluene", "108-88-3", "C7H8", "COMSOL"}, 
         {"biphenyl", "92-52-4", "C12H10", "COMSOL"}});
    model.thermodynamics().feature("pp1")
         .set("phase_list", new String[][]{{"Vapor", "Vapor"}, {"Liquid", "Liquid"}});
    model.thermodynamics().feature("pp1").label("\u6c7d-\u6db2\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("LiquidCard", "LiquidPhaseModel");
    model.thermodynamics().feature("pp1").set("EOSModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VLSurfaceTension", "Ideal");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").set("LiqDiffusivity", "WesselinghKrishna");
    model.thermodynamics().feature("pp1").set("LiqDiffusivityAtInfDilution", "Automatic");
    model.thermodynamics().feature("pp1").set("LLSurfaceTension", "None");
    model.thermodynamics().feature("pp1").set("LiqThermalConductivity", "Ideal");
    model.thermodynamics().feature("pp1").set("LiqViscosity", "LogarithmicMassMixing");
    model.thermodynamics().feature("pp1").set("LiqVolume", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase1").label("\u751f\u6210\u7113 1");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("funcname", "EnthalpyF_benzene_biphenyl_hydrogen_methane_toluene_Vapor11");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("property", "EnthalpyF");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("propertydescr", "\u751f\u6210\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("compounds", new String[]{"benzene", "biphenyl", "hydrogen", "methane", "toluene"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("phase", "Vapor");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"molefraction_benzene", "1", "\u6469\u5c14\u5206\u6570 benzene"}, 
         {"molefraction_biphenyl", "1", "\u6469\u5c14\u5206\u6570 biphenyl"}, 
         {"molefraction_hydrogen", "1", "\u6469\u5c14\u5206\u6570 hydrogen"}, 
         {"molefraction_methane", "1", "\u6469\u5c14\u5206\u6570 methane"}, 
         {"molefraction_toluene", "1", "\u6469\u5c14\u5206\u6570 toluene"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"molefraction_benzene", "0.2", "0.2"}, 
         {"molefraction_biphenyl", "0.2", "0.2"}, 
         {"molefraction_hydrogen", "0.2", "0.2"}, 
         {"molefraction_methane", "0.2", "0.2"}, 
         {"molefraction_toluene", "0.2", "0.2"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("derivatives", new String[]{"EnthalpyF_benzene_biphenyl_hydrogen_methane_toluene_Vapor11_Dtemperature", "EnthalpyF_benzene_biphenyl_hydrogen_methane_toluene_Vapor11_Dpressure", "EnthalpyF_benzene_biphenyl_hydrogen_methane_toluene_Vapor11_Dmolefraction_benzene", "EnthalpyF_benzene_biphenyl_hydrogen_methane_toluene_Vapor11_Dmolefraction_biphenyl", "EnthalpyF_benzene_biphenyl_hydrogen_methane_toluene_Vapor11_Dmolefraction_hydrogen", "EnthalpyF_benzene_biphenyl_hydrogen_methane_toluene_Vapor11_Dmolefraction_methane", "EnthalpyF_benzene_biphenyl_hydrogen_methane_toluene_Vapor11_Dmolefraction_toluene"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("funcname", "hF_mixture");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T_inlet", "1200[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("p_shell", "2.5*101325[Pa]", "\u538b\u529b\uff0c\u58f3\u4fa7");
    model.param().set("p_reactor", "2*101325[Pa]", "\u538b\u529b\uff0c\u53cd\u5e94\u5668");
    model.param().set("k", "8.0E-8[m^3/(N*s)]", "\u6bd4\u4f8b\u5e38\u6570");
    model.param().set("a", "100.5[1/m]", "\u5355\u4f4d\u4f53\u79ef\u9762\u79ef");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("f_H2", "k*a*(p_shell-p_reactor)*c_H2_shell", "\u819c\u8d28\u91cf\u6e90");
    model.component("comp1").variable("var1")
         .set("Q_mem", "f_H2*(hF_mixture(T_inlet,p_shell,0,0,1,0,0)-hF_mixture(re.T,p_reactor,0,0,1,0,0))", "\u819c\u70ed\u6e90");
    model.component("comp1").variable("var1")
         .set("y_C6H6", "re.F_C6H6/re.F0_C6H5CH3", "\u8f6c\u5316\u7387\uff0c\u82ef");
    model.component("comp1").variable("var1")
         .set("c_H2_shell", "p_shell/R_const/re.T", "\u6c22\u6d53\u5ea6\uff0c\u819c\u4fa7");

    model.component("comp1").physics("re").prop("reactor").set("reactor", "plugflow");
    model.component("comp1").physics("re").prop("energybalance").set("energybalance", "include");
    model.component("comp1").physics("re").prop("energybalance").set("Qextplug", "Q_mem");
    model.component("comp1").physics("re").prop("mixture").set("p", "p_reactor");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "C6H5CH3+H2=>C6H6+CH4");
    model.component("comp1").physics("re").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch1").set("Af", "5.67e9[1/s]");
    model.component("comp1").physics("re").feature("rch1").set("Ef", "228.2e3");
    model.component("comp1").physics("re").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch1").set("bulkFwdOrder", 1);
    model.component("comp1").physics("re").feature("rch1").set("r", "re.kf_1*re.c_C6H5CH3*(re.c_H2/1[mol/m^3])^0.5");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "2C6H6<=>C12H10+H2");
    model.component("comp1").physics("re").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch2").set("Af", "1e8");
    model.component("comp1").physics("re").feature("rch2").set("Ef", "167.5e3");
    model.component("comp1").physics("re").feature("rch2").set("Ar", "1e8");
    model.component("comp1").physics("re").feature("rch2").set("Er", "149.8e3");
    model.component("comp1").physics("re").create("add1", "AdditionalSourceFeature", -1);
    model.component("comp1").physics("re").feature("add1").setIndex("AddR", "f_H2", 4, 0);
    model.component("comp1").physics("re").feature("inits1").set("T0plug", "T_inlet");
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", 10, 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", 10, 4, 0);
    model.component("comp1").physics("re").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "biphenyl", 0, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "toluene", 1, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "benzene", 2, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "methane", 3, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "hydrogen", 4, 0);

    model.study("std1").label("\u7ba1\u5f0f\u53cd\u5e94\u5668");
    model.study("std1").feature("spf").set("useadvanceddisable", true);
    model.study("std1").feature("spf").set("disabledphysics", new String[]{"re/add1"});
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").run();
    model.result("pg1").label("\u6d53\u5ea6\u548c\u6e29\u5ea6\u66f2\u7ebf\uff0c\u7ba1\u5f0f\u53cd\u5e94\u5668");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u53cd\u5e94\u5668\u5bb9\u79ef (m<sup>3</sup>)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").set("yseclabel", "\u6e29\u5ea6 (K)");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").label("\u6d53\u5ea6");
    model.result("pg1").feature("glob1").setIndex("expr", "re.c_C6H5CH3", 0);
    model.result("pg1").feature("glob1").setIndex("expr", "re.c_H2", 1);
    model.result("pg1").feature("glob1").setIndex("expr", "re.c_C6H6", 2);
    model.result("pg1").feature("glob1").setIndex("expr", "re.c_CH4", 3);
    model.result("pg1").feature("glob1").setIndex("expr", "re.c_C12H10", 4);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u7532\u82ef", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "\u6c22", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "\u82ef", 2);
    model.result("pg1").feature("glob1").setIndex("legends", "\u7532\u70f7", 3);
    model.result("pg1").feature("glob1").setIndex("legends", "\u8054\u82ef", 4);
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").run();
    model.result("pg1").feature("glob2").label("\u6e29\u5ea6");
    model.result("pg1").feature("glob2").set("expr", new String[]{});
    model.result("pg1").feature("glob2").set("descr", new String[]{});
    model.result("pg1").feature("glob2").setIndex("expr", "re.T", 0);
    model.result("pg1").feature("glob2").set("linewidth", 2);
    model.result("pg1").feature("glob2").set("linemarker", "point");
    model.result("pg1").feature("glob2").set("markerpos", "interp");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("glob2").set("autoplotlabel", true);
    model.result("pg1").feature("glob2").set("autosolution", false);
    model.result("pg1").feature("glob2").set("autoexpr", false);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("ymax", 18);
    model.result("pg1").set("ymaxsec", 1370);
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("spf", "StationaryPlugflow");
    model.study("std2").feature("spf").set("plotgroup", "Default");
    model.study("std2").feature("spf").set("solnum", "auto");
    model.study("std2").feature("spf").set("notsolnum", "auto");
    model.study("std2").feature("spf").set("outputmap", new String[]{});
    model.study("std2").feature("spf").setSolveFor("/physics/re", true);
    model.study("std2").label("\u819c\u53cd\u5e94\u5668");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u6d53\u5ea6\u548c\u6e29\u5ea6\u66f2\u7ebf\uff0c\u819c\u53cd\u5e94\u5668");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").run();

    model.title("\u819c\u53cd\u5e94\u5668\u4e2d\u7684\u52a0\u6c22\u8131\u70f7\u57fa");

    model
         .description("\u672c\u4f8b\u5bf9\u819c\u53cd\u5e94\u5668\u4e2d\u8fdb\u884c\u7684\u70ed\u52a0\u6c22\u8131\u70f7\u57fa\u8fc7\u7a0b\u8fdb\u884c\u5efa\u6a21\u3002\u5176\u4e2d\u4f7f\u7528\u201c\u70ed\u529b\u5b66\u201d\u7279\u5f81\u6765\u5f15\u5165\u5185\u7f6e\u7684\u70ed\u529b\u5b66\u548c\u7269\u7406\u5c5e\u6027\u8ba1\u7b97\u3002");

    model.label("membrane_hda.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
