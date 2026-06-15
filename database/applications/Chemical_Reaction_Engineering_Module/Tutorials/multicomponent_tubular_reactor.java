/*
 * multicomponent_tubular_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:30 by COMSOL 6.3.0.290. */
public class multicomponent_tubular_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cpoxide");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cpoxide", "cpglycol"});
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("E", "75362[J/mol]", "\u6d3b\u5316\u80fd");
    model.param().set("A", "16.96e12[1/h]", "\u9891\u7387\u56e0\u5b50");
    model.param().set("Uk", "1300[W/m^2/K]", "\u603b\u4f20\u70ed\u7cfb\u6570");
    model.param().set("ke", "0.559[W/m/K]", "\u6df7\u5408\u7269\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("T0", "312[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("Ta0", "273[K]", "\u51b7\u5374\u6db2\u5165\u53e3\u6e29\u5ea6");
    model.param().set("dHrx", "-84666[J/mol]", "\u53cd\u5e94\u70ed");
    model.param().set("v0", "0.1[mol/s]/cpoxide0", "\u603b\u6d41\u7387");
    model.param().set("u0", "v0/(pi*Ra^2)", "\u5e73\u5747\u6d41\u901f");
    model.param()
         .set("cpoxide0", "rho_poxide/M_poxide/9[1]", "\u73af\u6c27\u4e19\u70f7\u6d53\u5ea6\uff0c\u5165\u53e3");
    model.param().set("cH2O0", "rho_H2O/M_H2O*(7/9)[1]", "\u6c34\u6d53\u5ea6\uff0c\u5165\u53e3");
    model.param().set("Ra", "0.1[m]", "\u53cd\u5e94\u5668\u534a\u5f84");
    model.param().set("L", "1[m]", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("M_poxide", "58.095[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u73af\u6c27\u4e19\u70f7");
    model.param().set("M_H2O", "18[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6c34");
    model.param().set("M_pglycol", "76.095[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u4e19\u4e8c\u9187");
    model.param().set("rho_poxide", "830[kg/m^3]", "\u5bc6\u5ea6\uff0c\u73af\u6c27\u4e19\u70f7");
    model.param().set("rho_H2O", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6c34");
    model.param().set("rho_pglycol", "1040[kg/m^3]", "\u5bc6\u5ea6\uff0c\u4e19\u4e8c\u9187");
    model.param().set("myref_H2O", "1e-3[Pa*s]", "\u53c2\u8003\u52a8\u529b\u9ecf\u5ea6\uff0c\u6c34");
    model.param().set("Tref_my", "293[K]", "\u53c2\u8003\u6e29\u5ea6\u9ecf\u5ea6");
    model.param().set("cpm_H2O", "75.36[J/mol/K]", "\u6469\u5c14\u70ed\u5bb9\uff0c\u6c34");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Ra", "L"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Xpoxide", "(cpoxide0-cpoxide)/cpoxide0");
    model.component("comp1").variable("var1").descr("Xpoxide", "\u73af\u6c27\u4e19\u70f7\u7684\u8f6c\u5316\u7387");

    model.component("comp1").physics("chem").prop("TPFeatureInput").set("T_src", "root.comp1.T");
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 2);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "poxide+H2O=>pglycol");
    model.component("comp1").physics("chem").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("r", "chem.kf_1*chem.c_poxide");
    model.component("comp1").physics("chem").feature("rch1").set("bulkFwdOrder", 1);
    model.component("comp1").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch1").set("Af", "A");
    model.component("comp1").physics("chem").feature("rch1").set("Ef", "E");
    model.component("comp1").physics("chem").feature("rch1").set("ReactionHeatSource", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("Q", "-chem.r_1*dHrx");
    model.component("comp1").physics("chem").feature("poxide").set("enableChemicalFormulaCheckbox", true);
    model.component("comp1").physics("chem").feature("poxide").set("chemicalFormula", "C3H6O");
    model.component("comp1").physics("chem").feature("poxide").set("rho", "rho_poxide");
    model.component("comp1").physics("chem").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("chem").feature("H2O").set("rho", "rho_H2O");
    model.component("comp1").physics("chem").feature("H2O").set("k", "ke");
    model.component("comp1").physics("chem").feature("H2O").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("H2O").set("Cp", "cpm_H2O");
    model.component("comp1").physics("chem").feature("pglycol").set("enableChemicalFormulaCheckbox", true);
    model.component("comp1").physics("chem").feature("pglycol").set("chemicalFormula", "C3H8O2");
    model.component("comp1").physics("chem").feature("pglycol").set("rho", "rho_pglycol");
    model.component("comp1").physics("chem").feature("rch1").set("formula", "poxide + H2O => pglycol");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "cH2O0", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cpglycol", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cpoxide", 2, 0);
    model.component("comp1").physics("chem").prop("calcTransport").set("etaRef", "myref_H2O");
    model.component("comp1").physics("chem").prop("calcTransport").set("Tref", "Tref_my");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cpoxide", new String[]{"chem.D_poxide", "0", "0", "0", "chem.D_poxide", "0", "0", "0", "chem.D_poxide"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cpglycol", new String[]{"chem.D_pglycol", "0", "0", "0", "chem.D_pglycol", "0", "0", "0", "chem.D_pglycol"});
    model.component("comp1").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1")
         .setIndex("R_cpoxide_src", "root.comp1.chem.R_poxide", 0);
    model.component("comp1").physics("tds").feature("reac1")
         .setIndex("R_cpglycol_src", "root.comp1.chem.R_pglycol", 0);
    model.component("comp1").physics("tds").create("in1", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in1").selection().set(2);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "cpoxide0", 0);
    model.component("comp1").physics("tds").feature("in1").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(3);
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "root.comp1.chem.krr");
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "root.comp1.chem.rho");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "root.comp1.chem.Cptot");
    model.component("comp1").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0_src", "root.comp1.chem.Qtot");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "Uk");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "Ta0");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(3);
    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "root.comp1.chem.rho");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "root.comp1.chem.eta");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(3);

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 2);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 0.01);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 200);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 0.01);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u6d53\u5ea6, poxide (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"cpoxide"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tds.tflux_cpoxider", "tds.tflux_cpoxidez"});
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 10);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").label("\u6d53\u5ea6, poxide, 3D (tds)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"cpoxide"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, pglycol (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cpglycol"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"tds.tflux_cpglycolr", "tds.tflux_cpglycolz"});
    model.result("pg3").feature("arws1").set("xnumber", 10);
    model.result("pg3").feature("arws1").set("ynumber", 10);
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").feature("arws1").create("sel1", "Selection");
    model.result("pg3").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").label("\u6d53\u5ea6, pglycol, 3D (tds)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cpglycol"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6 (ht)");
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u901f\u5ea6 (spf)");
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "spf.U");
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u538b\u529b (spf)");
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("con1", "Contour");
    model.result("pg7").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("expr", "p");
    model.result("pg7").feature("con1").set("number", 40);
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("colortable", "Rainbow");
    model.result("pg7").feature("con1").set("smooth", "internal");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u8868\u9762");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "spf.U");
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg9").set("dataisaxisym", "off");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("solutionparams", "parent");
    model.result("pg9").feature("surf1").set("expr", "nitf1.T");
    model.result("pg9").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg9").feature("surf1").set("smooth", "internal");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result("pg9").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg9").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg9").feature().create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg9").feature("arws1").set("showsolutionparams", "on");
    model.result("pg9").feature("arws1").set("solutionparams", "parent");
    model.result("pg9").feature("arws1").set("expr", new String[]{"nitf1.ur", "nitf1.uz"});
    model.result("pg9").feature("arws1").set("xnumber", 30);
    model.result("pg9").feature("arws1").set("ynumber", 30);
    model.result("pg9").feature("arws1").set("arrowtype", "cone");
    model.result("pg9").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("arws1").set("showsolutionparams", "on");
    model.result("pg9").feature("arws1").set("data", "parent");
    model.result("pg9").feature("arws1").feature().create("col1", "Color");
    model.result("pg9").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg9").feature("arws1").feature("col1").set("expr", "spf.U");
    model.result("pg9").feature("arws1").feature("col1").set("colortable", "Rainbow");
    model.result("pg9").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg9").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "Ra", 1, 0);
    model.result().dataset("cln1").set("genparaactive", true);
    model.result().dataset("cln1").set("genparadist", "0.5*L 1*L");
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("removesymelem", true);
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").set("data", "mir1");
    model.result("pg10").label("\u6e29\u5ea6\uff0c\u8868\u9762\u56fe\uff08\u955c\u50cf\uff09");
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u5f84\u5411\u4f4d\u7f6e (m)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u8f74\u5411\u4f4d\u7f6e (m)");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "T");
    model.result("pg10").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u8f6c\u5316\u7387\uff0c\u8868\u9762\u56fe\uff08\u955c\u50cf\uff09");
    model.result("pg11").run();
    model.result("pg11").feature("surf1").set("expr", "Xpoxide");
    model.result("pg11").feature("surf1").set("descr", "\u73af\u6c27\u4e19\u70f7\u7684\u8f6c\u5316\u7387");
    model.result("pg11").feature("surf1").set("colortable", "Lichen");
    model.result("pg11").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg4").run();
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortable", "Bryophyta");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u8868\u9762\uff1a\u6e29\u5ea6");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u7b49\u503c\uff1a\u6e29\u5ea6");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").set("title", "\u8868\u9762\uff1a\u901f\u5ea6\u5927\u5c0f");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u7b49\u503c\uff1a\u538b\u529b");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg8").run();
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u8868\u9762\uff1a\u901f\u5ea6\u5927\u5c0f");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("colortable", "Metasepia");
    model.result("pg8").feature("surf1").set("colortabletrans", "reverse");
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u6e29\u5ea6\uff0c\u66f2\u7ebf");
    model.result("pg12").set("data", "cln1");
    model.result("pg12").set("titletype", "none");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "\u5f84\u5411\u4f4d\u7f6e (m)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u6e29\u5ea6 (K)");
    model.result("pg12").set("legendlayout", "outside");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").set("expr", "T");
    model.result("pg12").feature("lngr1").set("descr", "\u6e29\u5ea6");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("legendmethod", "manual");
    model.result("pg12").feature("lngr1").setIndex("legends", "\u5165\u53e3", 0);
    model.result("pg12").feature("lngr1").setIndex("legends", "\u534a\u8f74\u4f4d\u7f6e", 1);
    model.result("pg12").feature("lngr1").setIndex("legends", "\u51fa\u53e3", 2);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result().duplicate("pg13", "pg12");
    model.result("pg13").run();
    model.result("pg13").label("\u8f6c\u5316\u7387\uff0c\u66f2\u7ebf");
    model.result("pg13").set("ylabel", "\u8f6c\u5316\u7387");
    model.result("pg13").run();
    model.result("pg13").feature("lngr1").set("expr", "Xpoxide");
    model.result("pg13").feature("lngr1").set("descr", "\u73af\u6c27\u4e19\u70f7\u7684\u8f6c\u5316\u7387");
    model.result("pg13").run();
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result().remove("pg3");
    model.result().remove("pg5");
    model.result().remove("pg6");
    model.result().remove("pg9");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u5e26\u7b49\u6e29\u51b7\u5374\u7684\u591a\u7ec4\u5206\u7ba1\u5f0f\u53cd\u5e94\u5668");

    model
         .description("\u672c\u6a21\u578b\u63cf\u8ff0\u7ba1\u5f0f\u53cd\u5e94\u5668\u4e2d\u73af\u6c27\u4e19\u70f7 (A) \u4e0e\u6c34 (B) \u53d1\u751f\u53cd\u5e94\u4ea7\u751f\u4e19\u4e8c\u9187 (C)\uff1a\n\nA + B -> C\n\n\u8fd9\u91cc\u6c34\u662f\u6eb6\u5242\uff0c\u53cd\u5e94\u52a8\u529b\u5b66\u53ef\u4ee5\u63cf\u8ff0\u4e3a\u73af\u6c27\u4e19\u70f7\u7684\u4e00\u7ea7\u53cd\u5e94\n\nR = k*C_A\n\n\u6b64\u53cd\u5e94\u4e3a\u653e\u70ed\u53cd\u5e94\uff0c\u56e0\u6b64\u4f7f\u7528\u7b49\u6e29\u51b7\u5374\u5939\u5957\u4f7f\u53cd\u5e94\u5668\u964d\u6e29\u3002\n\n\u53cd\u5e94\u5668\u5728\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u5f0f\u4e0b\u5efa\u6a21\uff0c\u6839\u636e\u4eff\u771f\u7ed3\u679c\u53ef\u77e5\uff0c\u6210\u5206\u548c\u6e29\u5ea6\u5728\u5f84\u5411\u548c\u8f74\u5411\u90fd\u53d1\u751f\u4e86\u53d8\u5316\u3002\u201c\u5316\u5b66\u201d\u63a5\u53e3\u7528\u4e8e\u5c06\u53cd\u5e94\u52a8\u529b\u5b66\u548c\u8d28\u91cf\u4f20\u9012\u5c5e\u6027\u76f8\u7ed3\u5408\u3002");

    model.label("multicomponent_tubular_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
