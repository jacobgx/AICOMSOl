/*
 * phase_envelope.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:39 by COMSOL 6.3.0.290. */
public class phase_envelope {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Liquid_and_Gas_Properties_Module\\Tutorials");

    model.param().set("P", "1[atm]");
    model.param().descr("P", "\u538b\u529b");
    model.param().set("xCh", ".5");
    model.param().descr("xCh", "\u6c2f\u4eff\u7684\u6469\u5c14\u5206\u6570");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"chloroform", "67-66-3", "CHCl3", "COMSOL"}, {"methanol", "67-56-1", "CH4O", "COMSOL"}});
    model.thermodynamics().feature("pp1")
         .set("phase_list", new String[][]{{"Vapor", "Vapor"}, {"Liquid", "Liquid"}});
    model.thermodynamics().feature("pp1").label("\u6c7d-\u6db2\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "UNIFAC");
    model.thermodynamics().feature("pp1").set("EOS", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "UNIFAC");
    model.thermodynamics().feature("pp1").set("LiquidCard", "LiquidPhaseModel");
    model.thermodynamics().feature("pp1").set("EOSModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("EOS", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VLSurfaceTension", "Ideal");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").set("LiqDiffusivity", "WesselinghKrishna");
    model.thermodynamics().feature("pp1").set("LiqDiffusivityAtInfDilution", "Automatic");
    model.thermodynamics().feature("pp1").set("LLSurfaceTension", "None");
    model.thermodynamics().feature("pp1").set("LiqThermalConductivity", "Ideal");
    model.thermodynamics().feature("pp1").set("LiqViscosity", "LogarithmicMassMixing");
    model.thermodynamics().feature("pp1").set("LiqVolume", "EOS");
    model.thermodynamics().feature("pp1").set("PoyntingFactor", "off");
    model.thermodynamics().feature("pp1").set("UseSaturatedVaporFugacity", "off");
    model.thermodynamics().feature("pp1")
         .set("property", new String[]{"Automatic", "Ideal", "KineticTheory", "Brokaw", "WesselinghKrishna", "Automatic", "None", "Ideal", "LogarithmicMassMixing", "EOS", 
         "off", "off"});
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature().create("flashcalc1", "FlashCalculationProperty");
    model.thermodynamics().feature("pp1").feature().remove("flashcalc1");
    model.thermodynamics().feature("pp1").feature().create("flashcalc1", "FlashCalculationProperty");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("funcname", "flashcalc1");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("property", "FlashCalculationProperty");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("propertydescr", "\u5e73\u8861\u8ba1\u7b97");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("dvars", new String[]{"0", "0", "0", "0"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("compounds", new String[]{"chloroform", "methanol"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("functions", new String[][]{{"Flash1_1_PhaseExist_Vapor", "1", "Presence of Vapor phase"}, 
         {"Flash1_1_PhaseExist_Liquid", "1", "Presence of Liquid phase"}, 
         {"Flash1_1_Temperature", "K", "Temperature"}, 
         {"Flash1_1_PhaseAmount_Vapor", "mol", "Amount in Vapor phase"}, 
         {"Flash1_1_PhaseAmount_Liquid", "mol", "Amount in Liquid phase"}, 
         {"Flash1_1_PhaseComposition_Vapor_chloroform", "mol/mol", "Fraction of chloroform in Vapor phase"}, 
         {"Flash1_1_PhaseComposition_Vapor_methanol", "mol/mol", "Fraction of methanol in Vapor phase"}, 
         {"Flash1_1_PhaseComposition_Liquid_chloroform", "mol/mol", "Fraction of chloroform in Liquid phase"}, 
         {"Flash1_1_PhaseComposition_Liquid_methanol", "mol/mol", "Fraction of methanol in Liquid phase"}});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("cond1", new String[]{"pressure"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("cond2", new String[]{"phasefraction", "mole", "Vapor"});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("inphase", "Flash1_1_PhaseExist");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("temperature", "Flash1_1_Temperature");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("pressure", "Flash1_1_Pressure");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("amounts", "Flash1_1_PhaseAmount");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("composition", "Flash1_1_PhaseComposition");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("soltype", "undefined");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("phases", new String[]{"Vapor", "Liquid"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("args", new String[][]{{"pressure", "Pa", "\u538b\u529b"}, 
         {"phasefraction", "mol/mol", "Vapor \u6469\u5c14\u5206\u6570"}, 
         {"chloroform", "mol", "\u6570\u91cf chloroform"}, 
         {"methanol", "mol", "\u6570\u91cf methanol"}});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("plotargs", new String[][]{{"pressure", "101325", "101325"}, 
         {"phasefraction", "0", "0"}, 
         {"chloroform", "0", "0"}, 
         {"methanol", "0", "0"}});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("derivatives", "");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("SecondDerivatives", "");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("SecondDerivativeIndices", "");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("plotfunction", "Flash1_1_PhaseExist_Vapor");
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.variable().create("var1");
    model.variable("var1").set("xM", "1-xCh");
    model.variable("var1").descr("xM", "\u7532\u9187\u7684\u6469\u5c14\u5206\u6570");
    model.variable("var1").set("T_DewPoint", "Flash1_1_Temperature(P,1,xCh,xM)");
    model.variable("var1").descr("T_DewPoint", "\u9732\u70b9");
    model.variable("var1").set("T_BubblePoint", "Flash1_1_Temperature(P,0,xCh,xM)");
    model.variable("var1").descr("T_BubblePoint", "\u6ce1\u70b9");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").label("\u7814\u7a76 1 - \u9732\u70b9\u548c\u6cb8\u70b9\u66f2\u7ebf");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").setIndex("pname", "P", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "P", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "xCh", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.01,1)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("T-x \u56fe");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u6c2f\u4eff\u6469\u5c14\u5206\u6570");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6e29\u5ea6/K");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "T_DewPoint", 0);
    model.result("pg1").feature("glob1").setIndex("expr", "T_BubblePoint", 1);
    model.result("pg1").feature("glob1").set("titletype", "manual");
    model.result("pg1").feature("glob1").set("title", "\u6c2f\u4eff-\u7532\u9187\u76f8\u5305\u7edc\u7ebf");
    model.result("pg1").feature("glob1").set("linestyle", "cycle");
    model.result("pg1").feature("glob1").set("linewidth", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("text", "\u5171\u6cb8\u7ec4\u5408\u7269");
    model.result("pg1").feature("ann1").set("posyexpr", 326.03);
    model.result("pg1").feature("ann1").set("posxexpr", 0.66);
    model.result("pg1").feature("ann1").set("anchorpoint", "uppermiddle");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("ymin", 324);
    model.result("pg1").set("ymax", 339);
    model.result("pg1").run();

    model.param().set("n", "0");
    model.param().descr("n", "\u6c14\u76f8\u5206\u6570");

    model.variable("var1").set("T_iso", "0.5*(T_DewPoint+T_BubblePoint)");
    model.variable("var1").descr("T_iso", "\u7b49\u6e29\u8fde\u63a5\u7ebf");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("ge", "GlobalEquations");

    model.study("std1").feature("stat").setSolveFor("/physics/ge", false);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "xCh_at_n", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "T_iso-Flash1_1_Temperature(P, n, xCh_at_n[mol], (1-xCh_at_n)[mol])", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "xCh", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "temperature");
    model.component("comp1").physics("ge").feature("ge1").label("\u7b2c n \u76f8\u5728 T_iso \u65f6\u7684 xCh");

    model.thermodynamics().feature("pp1").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase1").label("\u7113 1");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("funcname", "Enthalpy_chloroform_methanol_Vapor11");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("property", "Enthalpy");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("propertydescr", "\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("compounds", new String[]{"chloroform", "methanol"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("phase", "Vapor");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"molefraction_chloroform", "1", "\u6469\u5c14\u5206\u6570 chloroform"}, 
         {"molefraction_methanol", "1", "\u6469\u5c14\u5206\u6570 methanol"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"molefraction_chloroform", "0.5", "0.5"}, 
         {"molefraction_methanol", "0.5", "0.5"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("derivatives", new String[]{"Enthalpy_chloroform_methanol_Vapor11_Dtemperature", "Enthalpy_chloroform_methanol_Vapor11_Dpressure", "Enthalpy_chloroform_methanol_Vapor11_Dmolefraction_chloroform", "Enthalpy_chloroform_methanol_Vapor11_Dmolefraction_methanol"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("funcname", "hv");
    model.thermodynamics().feature("pp1").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase2").label("\u7113 2");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("funcname", "Enthalpy_chloroform_methanol_Liquid12");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("property", "Enthalpy");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("propertydescr", "\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("compounds", new String[]{"chloroform", "methanol"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("phase", "Liquid");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"molefraction_chloroform", "1", "\u6469\u5c14\u5206\u6570 chloroform"}, 
         {"molefraction_methanol", "1", "\u6469\u5c14\u5206\u6570 methanol"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"molefraction_chloroform", "0.5", "0.5"}, 
         {"molefraction_methanol", "0.5", "0.5"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("derivatives", new String[]{"Enthalpy_chloroform_methanol_Liquid12_Dtemperature", "Enthalpy_chloroform_methanol_Liquid12_Dpressure", "Enthalpy_chloroform_methanol_Liquid12_Dmolefraction_chloroform", "Enthalpy_chloroform_methanol_Liquid12_Dmolefraction_methanol"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("funcname", "hl");

    model.param().set("Tref", "273.15[K]");
    model.param().descr("Tref", "\u53c2\u8003\u6e29\u5ea6");

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("xM_at_n", "1-comp1.xCh_at_n");
    model.component("comp1").variable("var2").set("hVapor", "hv(T_iso,P,comp1.xCh_at_n,xM_at_n)");
    model.component("comp1").variable("var2").descr("hVapor", "\u84b8\u6c7d\u7113");
    model.component("comp1").variable("var2").set("hLiquid", "hl(T_iso,P,comp1.xCh_at_n,xM_at_n)");
    model.component("comp1").variable("var2").descr("hLiquid", "\u6db2\u4f53\u7113");
    model.component("comp1").variable("var2").set("hMix", "n*hVapor+(1-n)*hLiquid");
    model.component("comp1").variable("var2")
         .descr("hMix", "\u84b8\u6c7d/\u6db2\u4f53\u6df7\u5408\u7269\u7684\u7113");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ge", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "P", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "P", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "xCh", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "1e-6 range(0.1,0.1,0.9) 1-1e-6", 0);
    model.study("std2").feature("stat").setIndex("pname", "P", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 1);
    model.study("std2").feature("stat").setIndex("pname", "P", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 1);
    model.study("std2").feature("stat").setIndex("pname", "n", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,.1,1)", 1);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").feature("stat").set("pcontinuationmode", "manual");
    model.study("std2").feature("stat").set("pcontinuation", "xCh");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"xCh_at_n"});
    model.result().numerical("gev1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf xCh_at_n"});

    model.study("std2").label("\u7814\u7a76 2 - \u7b49\u6e29\u66f2\u7ebf");

    model.result().setOnlyPlotWhenRequested(true);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").label("H-x \u56fe");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("legendpos", "center");
    model.result("pg2").feature("glob1").label("\u9732\u7ebf");
    model.result("pg2").feature("glob1").set("data", "dset2");
    model.result("pg2").feature("glob1").setIndex("looplevelinput", "last", 1);
    model.result("pg2").feature("glob1").setIndex("expr", "hv(T_DewPoint,P,xCh,xM)", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "kJ/mol", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "", 0);
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("linestyle", "dashed");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("autoplotlabel", true);
    model.result("pg2").feature("glob1").set("autosolution", false);
    model.result("pg2").feature("glob1").set("autodescr", false);
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").label("\u6cb8\u817e\u7ebf");
    model.result("pg2").feature("glob2").set("data", "dset2");
    model.result("pg2").feature("glob2").setIndex("looplevelinput", "first", 1);
    model.result("pg2").feature("glob2").setIndex("expr", "hl(T_BubblePoint,P,xCh,1-xCh)", 0);
    model.result("pg2").feature("glob2").setIndex("unit", "kJ/mol", 0);
    model.result("pg2").feature("glob2").setIndex("descr", "", 0);
    model.result("pg2").feature("glob2").set("titletype", "none");
    model.result("pg2").feature("glob2").set("linestyle", "dotted");
    model.result("pg2").feature("glob2").set("linewidth", 2);
    model.result("pg2").feature("glob2").set("autoplotlabel", true);
    model.result("pg2").feature("glob2").set("autosolution", false);
    model.result("pg2").feature("glob2").set("autodescr", false);
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u6c2f\u4eff\u6469\u5c14\u5206\u6570");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7113/kJ \\cdot mol<sup>-1</sup>");
    model.result("pg2").create("glob3", "Global");
    model.result("pg2").feature("glob3").set("markerpos", "datapoints");
    model.result("pg2").feature("glob3").set("linewidth", "preference");
    model.result("pg2").feature("glob3").label("\u7b49\u6e29\u7ebf");
    model.result("pg2").feature("glob3").set("data", "dset2");
    model.result("pg2").feature("glob3").setIndex("expr", "hMix", 0);
    model.result("pg2").feature("glob3").setIndex("unit", "kJ/mol", 0);
    model.result("pg2").feature("glob3").setIndex("descr", "", 0);
    model.result("pg2").feature("glob3").set("titletype", "manual");
    model.result("pg2").feature("glob3")
         .set("title", "\u4ee5\u989c\u8272\u6807\u8bc6\u6e29\u5ea6 (K) \u7684 H-x \u56fe\uff0c\u5b9e\u7ebf\u63cf\u8ff0\u7b49\u6e29\u8fde\u63a5\u7ebf\u3002");
    model.result("pg2").feature("glob3").set("xdata", "expr");
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "level2");
    model.result("pg2").feature("glob3").set("linewidth", 2);
    model.result("pg2").feature("glob3").set("legend", false);
    model.result("pg2").feature("glob1").create("col1", "Color");
    model.result("pg2").feature("glob1").feature("col1").set("expr", "T_DewPoint");
    model.result("pg2").feature("glob1").feature("col1").set("colortable", "Viridis");
    model.result("pg2").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("glob2").feature().copy("col1", "pg2/glob1/col1");
    model.result("pg2").feature("glob2").feature("col1").set("expr", "T_BubblePoint");
    model.result("pg2").feature("glob3").feature().copy("col1", "pg2/glob1/col1");
    model.result("pg2").feature("glob3").feature("col1").set("expr", "T_iso");
    model.result("pg2").feature("glob3").feature("col1").set("colorlegend", true);
    model.result("pg2").run();

    model.title("\u4f7f\u7528\u5e73\u8861\u8ba1\u7b97\u521b\u5efa\u76f8\u5305\u7edc\u7ebf");

    model
         .description("\u672c\u4f8b\u4e3a\u975e\u7406\u60f3\u7684\u6c2f\u4eff/\u7532\u9187\u6df7\u5408\u7269\u6784\u5efa\u76f8\u5305\u7edc\u7ebf\u3002\u9996\u5148\uff0c\u6784\u5efa\u6e29\u5ea6-\u7ec4\u6210\u56fe\uff0c\u4ee5\u7a81\u51fa\u663e\u793a\u6df7\u5408\u7269\u7684\u5171\u6cb8\u7269\u3002\u6b64\u5916\uff0c\u8fd8\u4f1a\u751f\u6210\u7113-\u7ec4\u6210\u56fe\uff0c\u5e76\u7ed8\u5236\u7b49\u6e29\u7ebf\u3002\u8fd9\u4e9b\u7c7b\u578b\u7684\u56fe\u5bf9\u4e8e\u7406\u89e3\u84b8\u998f\u6216\u95ea\u84b8\u7b49\u8fc7\u7a0b\u81f3\u5173\u91cd\u8981\u3002\u8be5\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u70ed\u529b\u5b66\u201d\u8282\u70b9\u6765\u521b\u5efa\u8fd9\u7c7b\u719f\u6089\u7684\u56fe\u3002");

    model.label("phase_envelope.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
