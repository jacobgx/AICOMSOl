/*
 * boat_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:24 by COMSOL 6.3.0.290. */
public class boat_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cSiH4");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cSiH4", "cH2"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R_w", "40[mm]", "\u6676\u5706\u534a\u5f84");
    model.param().set("R_curve", "40[mm]", "\u53cd\u5e94\u5668\u66f2\u7387\u534a\u5f84");
    model.param().set("R_tube", "20[mm]", "\u7ba1\u534a\u5f84");
    model.param().set("d_cc", "2.5[mm]", "\u6676\u5706\u95f4\u8ddd");
    model.param().set("d_w", "0.5[mm]", "\u6676\u5706\u539a\u5ea6");
    model.param().set("W", "60[mm]", "\u53cd\u5e94\u5668\u5bbd\u5ea6");
    model.param().set("L", "180[mm]", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("L_space", "40[mm]", "\u53cd\u5e94\u5668\u5165\u53e3\u4e0e\u6676\u5706\u675f\u95f4\u8ddd");
    model.param().set("L_tube", "40[mm]", "\u7ba1\u957f\u5ea6");
    model.param().set("L_wb", "L-2*L_space", "\u6676\u5706\u675f\u9ad8\u5ea6");
    model.param().set("H_sb", "5[mm]", "\u6676\u821f\u9ad8\u5ea6");
    model.param().set("b", "2.5[mm]", "\u58c1\u539a\u5ea6");
    model.param().set("S_a", "2*(R_w^2+R_w*d_w)/(R_w^2*d_cc)", "\u6bd4\u8868\u9762\u79ef");
    model.param().set("e_p", "(d_cc-d_w)/d_cc", "\u6676\u5706\u675f\u5b54\u9699\u7387");
    model.param().set("kappa_r", "1E-9[m^2]", "\u6676\u5706\u675f\u6e17\u900f\u7387 rr");
    model.param().set("kappa_z", "1E-12[m^2]", "\u6676\u5706\u675f\u6e17\u900f\u7387 zz");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("p_tot", "25[Pa]", "\u7cfb\u7edf\u603b\u538b");
    model.param("par2").set("T_r", "873[K]", "\u53cd\u5e94\u5668\u6e29\u5ea6");
    model.param("par2").set("v0", "1[m/s]", "\u5165\u53e3\u8f74\u5411\u6d41\u901f");
    model.param("par2").set("x_SiH4", "0.2", "\u5165\u53e3\u7845\u70f7\u6469\u5c14\u5206\u6570");
    model.param("par2").set("x_N2", "1-x_SiH4", "\u5165\u53e3\u6c2e\u6c14\u6469\u5c14\u5206\u6570");
    model.param("par2")
         .set("a1", "9.13E8[m/(K*s)]*T_r*exp(-3.80E-19[J]/(k_B_const*T_r))", "\u52a8\u529b\u5b66\u53c2\u6570 1");
    model.param("par2")
         .set("b1", "3.83E9[1/Pa]*exp(-2.90E-19[J]/(k_B_const*T_r))", "\u52a8\u529b\u5b66\u53c2\u6570 2");
    model.param("par2").set("k_rxn", "a1/((1+b1*x_SiH4*p_tot))", "\u901f\u7387\u5e38\u6570");
    model.param("par2").set("c_SiH4", "x_SiH4*p_tot/(R_const*T_r)", "\u7845\u70f7\u521d\u59cb\u6d53\u5ea6");
    model.param("par2").set("c_N2", "x_N2*p_tot/(R_const*T_r)", "\u6c2e\u6c14\u521d\u59cb\u6d53\u5ea6");
    model.param("par2").set("rho_Si", "2e3[kg/m^3]", "\u7845\u5bc6\u5ea6");
    model.param("par2").set("M_Si", "28[g/mol]", "\u7845\u6469\u5c14\u8d28\u91cf");

    model.component("comp1").geom("geom1").run("");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r1");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "L"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r1", 3);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "R_curve");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"R_tube", "L_tube"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-L_tube"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"R_tube", "L_tube"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "L"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("fil1", "r2", "r3");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("point").set("uni1", 6, 7, 9);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", "b");
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("thi1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("thi1").selection("input")
         .set("fil2", 6, 7, 8, 9, 10, 11, 12, 13);
    model.component("comp1").geom("geom1").feature("thi1").set("keep", true);
    model.component("comp1").geom("geom1").feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("thi1").set("upthick", "b");
    model.component("comp1").geom("geom1").run("thi1");
    model.component("comp1").geom("geom1").create("fil3", "Fillet");
    model.component("comp1").geom("geom1").feature("fil3").selection("point").set("thi1", 7, 9);
    model.component("comp1").geom("geom1").feature("fil3").set("radius", "b");
    model.component("comp1").geom("geom1").run("fil3");
    model.component("comp1").geom("geom1").run("fil3");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("r4", false);
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("r4");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"R_w", "L_wb"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "L_space"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"R_w", "H_sb"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"0", "L_space"});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"R_w", "H_sb"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"0", "L_space+L_wb-H_sb"});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(3);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(1, 3);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(2, 4, 5);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Delta_Si", "k_rxn*cSiH4*M_Si/rho_Si");
    model.component("comp1").variable("var1").descr("Delta_Si", "Deposition Rate");

    model.component("comp1").physics("chem").prop("TPFeatureInput").set("T_src", "userdef");
    model.component("comp1").physics("chem").prop("TPFeatureInput").set("T", "T_r");
    model.component("comp1").physics("chem").prop("TPFeatureInput").set("p_src", "userdef");
    model.component("comp1").physics("chem").prop("TPFeatureInput").set("p", "p_tot");
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 2);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "SiH4 => Si(s) + 2 H2");
    model.component("comp1").physics("chem").feature("rch1").set("kf", "k_rxn*S_a");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 2);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "N2");
    model.component("comp1").physics("chem").feature("N2").set("sType", "solvent");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cH2", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "c_N2", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cSiH4", 2, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SolidConcentration", 0, 0, 0);
    model.component("comp1").physics("spf").selection().set(1);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p_tot");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Tref", "T_r");
    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "root.comp1.chem.rho");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "root.comp1.chem.eta");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "v0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(13);
    model.component("comp1").physics("tds").selection().named("sel2");
    model.component("comp1").physics("tds").prop("TransportMechanism").set("MassTransferInPorousMedia", true);
    model.component("comp1").physics("tds").feature("cdm1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("tds").feature("cdm1").set("minput_temperature", "T_r");
    model.component("comp1").physics("tds").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").feature("cdm1").set("DiffusionCoefficientSource", "chem");
    model.component("comp1").physics("tds").feature("cdm1").set("Dchem_cSiH4_src", "root.comp1.chem.DRR_SiH4");
    model.component("comp1").physics("tds").feature("cdm1").set("Dchem_cH2_src", "root.comp1.chem.DRR_H2");
    model.component("comp1").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cSiH4_src", "root.comp1.chem.R_SiH4", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cH2_src", "root.comp1.chem.R_H2", 0);
    model.component("comp1").physics("tds").feature("reac1").set("ReactingVolumeType", "PoreVolume");
    model.component("comp1").physics("tds").feature("reac1").selection().named("sel1");
    model.component("comp1").physics("tds").create("in1", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in1").selection().set(2);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "c_SiH4", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(13);
    model.component("comp1").physics("tds").create("porous1", "PorousMedium", 2);
    model.component("comp1").physics("tds").feature("porous1").selection().named("sel1");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1").set("minput_temperature", "T_r");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("DiffusionCoefficientSource", "chem");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("Dchem_cSiH4", new String[]{"chem.D_SiH4", "0", "0", "0", "chem.D_SiH4", "0", "0", "0", "0"});
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("Dchem_cH2", new String[]{"chem.D_H2", "0", "0", "0", "chem.D_H2", "0", "0", "0", "0"});
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("FluidDiffusivityModelType", "UserDefined");
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro", "e_p");

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 2);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").feature("param").setIndex("pname", "a1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param").setIndex("pname", "a1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param").setIndex("pname", "b", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "b", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "T_r", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(525, 25, 600)", 0);
    model.study("std1").feature("param").setIndex("punit", "degC", 0);
    model.study("std1").feature("param").setIndex("pname", "p_tot", 1);
    model.study("std1").feature("param").setIndex("plistarr", "range(10,15,40)", 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").setIndex("looplevel", 4, 1);
    model.result("pg4").label("\u6d53\u5ea6, SiH4 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cSiH4"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tds.tflux_cSiH4r", "tds.tflux_cSiH4z"});
    model.result("pg4").feature("arws1").set("xnumber", 10);
    model.result("pg4").feature("arws1").set("ynumber", 10);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(1, 3);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "rev1");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").setIndex("looplevel", 4, 1);
    model.result("pg5").label("\u6d53\u5ea6, SiH4, 3D (tds)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"cSiH4"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").setIndex("looplevel", 4, 1);
    model.result("pg6").label("\u6d53\u5ea6, H2 (tds)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cH2"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"tds.tflux_cH2r", "tds.tflux_cH2z"});
    model.result("pg6").feature("arws1").set("xnumber", 10);
    model.result("pg6").feature("arws1").set("ynumber", 10);
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").feature("arws1").create("sel1", "Selection");
    model.result("pg6").feature("arws1").feature("sel1").selection().set(1, 3);
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "rev1");
    model.result("pg7").setIndex("looplevel", 3, 0);
    model.result("pg7").setIndex("looplevel", 4, 1);
    model.result("pg7").label("\u6d53\u5ea6, H2, 3D (tds)");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"cH2"});
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "Kyanite");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").run();
    model.result("pg2").feature("con1").set("colortable", "Kyanite");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "Kyanite");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortable", "Agama");
    model.result("pg4").run();
    model.result().duplicate("pg8", "pg4");
    model.result("pg8").run();
    model.result().move("pg8", 4);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().named("sel1");
    model.result("pg8").run();
    model.result("pg8").feature("arws1").active(false);
    model.result("pg8").run();
    model.result("pg8").create("con1", "Contour");
    model.result("pg8").feature("con1").set("expr", "cSiH4");
    model.result("pg8").feature("con1").set("titletype", "none");
    model.result("pg8").feature("con1").set("number", 10);
    model.result("pg8").feature("con1").set("coloring", "uniform");
    model.result("pg8").feature("con1").set("color", "white");
    model.result("pg8").feature("con1").set("colorlegend", false);
    model.result("pg8").feature("con1").create("sel1", "Selection");
    model.result("pg8").feature("con1").feature("sel1").selection().named("sel1");
    model.result("pg8").run();
    model.result("pg8").set("edges", false);
    model.result("pg8").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("colortable", "Agama");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 2, 0);
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("colortable", "Caissara");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 2, 0);
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("colortable", "Caissara");
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", "L/2");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("data", "cpt1");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("showgrid", false);
    model.result("pg9").set("legendpos", "upperleft");
    model.result("pg9").create("ptgr1", "PointGraph");
    model.result("pg9").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr1").set("linewidth", "preference");
    model.result("pg9").feature("ptgr1").set("expr", "Delta_Si");
    model.result("pg9").feature("ptgr1").set("descractive", true);
    model.result("pg9").feature("ptgr1").set("xdatasolnumtype", "level2");
    model.result("pg9").feature("ptgr1").set("xdata", "expr");
    model.result("pg9").feature("ptgr1").set("xdataexpr", "T_r");
    model.result("pg9").feature("ptgr1").set("xdataunit", "\u00b0C");
    model.result("pg9").feature("ptgr1").set("xdatadescractive", true);
    model.result("pg9").feature("ptgr1").set("linestyle", "dashed");
    model.result("pg9").feature("ptgr1").set("linemarker", "point");
    model.result("pg9").feature("ptgr1").set("autopoint", false);
    model.result("pg9").feature("ptgr1").set("legend", true);
    model.result("pg9").run();
    model.result().dataset().create("max1", "Maximum");
    model.result().dataset().create("min1", "Minimum");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").set("data", "max1");
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("showgrid", false);
    model.result("pg10").set("legendlayout", "outside");
    model.result("pg10").set("legendposoutside", "bottom");
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").set("data", "max1");
    model.result("pg10").feature("ptgr1").set("descractive", true);
    model.result("pg10").feature("ptgr1").set("expr", "Delta_Si");
    model.result("pg10").feature("ptgr1").set("xdatasolnumtype", "level2");
    model.result("pg10").feature("ptgr1").set("xdata", "expr");
    model.result("pg10").feature("ptgr1").set("xdataexpr", "T_r");
    model.result("pg10").feature("ptgr1").set("xdataunit", "\u00b0C");
    model.result("pg10").feature("ptgr1").set("linestyle", "dashed");
    model.result("pg10").feature("ptgr1").set("linemarker", "point");
    model.result("pg10").feature("ptgr1").set("legend", true);
    model.result("pg10").feature("ptgr1").set("autopoint", false);
    model.result("pg10").create("ptgr2", "PointGraph");
    model.result("pg10").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr2").set("linewidth", "preference");
    model.result("pg10").feature("ptgr2").set("data", "min1");
    model.result("pg10").feature("ptgr2").set("expr", "Delta_Si");
    model.result("pg10").feature("ptgr2").set("descractive", true);
    model.result("pg10").feature("ptgr2").set("xdatasolnumtype", "level2");
    model.result("pg10").feature("ptgr2").set("xdata", "expr");
    model.result("pg10").feature("ptgr2").set("xdataexpr", "T_r");
    model.result("pg10").feature("ptgr2").set("xdataunit", "\u00b0C");
    model.result("pg10").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg10").feature("ptgr2").set("linemarker", "asterisk");
    model.result("pg10").feature("ptgr2").set("legend", true);
    model.result("pg10").feature("ptgr2").set("autopoint", false);
    model.result("pg10").feature("ptgr2").set("linecolor", "cyclereset");
    model.result("pg10").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/chem", true);
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/tds", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/rfd1", true);
    model.study("std2").feature("time").set("tunit", "min");
    model.study("std2").feature("time").set("tlist", "range(0,5,30)");
    model.study("std2").feature("time").setSolveFor("/physics/chem", false);
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/tds", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/rfd1", false);
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").feature("time").set("notsolnum", 8);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("cpt2", "CutPoint2D");
    model.result().dataset("cpt2").set("data", "dset2");
    model.result().dataset("cpt2").set("pointx", 0);
    model.result().dataset("cpt2").set("pointy", "L/2");
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").set("data", "cpt2");
    model.result("pg11").set("titletype", "none");
    model.result("pg11").set("showgrid", false);
    model.result("pg11").create("ptgr1", "PointGraph");
    model.result("pg11").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr1").set("linewidth", "preference");
    model.result("pg11").feature("ptgr1").set("expr", "Delta_Si*t");
    model.result("pg11").feature("ptgr1").set("unit", "nm");
    model.result("pg11").feature("ptgr1").set("descractive", true);
    model.result("pg11").feature("ptgr1").set("linestyle", "dashed");
    model.result("pg11").feature("ptgr1").set("linemarker", "point");
    model.result("pg11").run();
    model.result().dataset().create("max2", "Maximum");
    model.result().dataset("max2").set("data", "dset2");
    model.result().dataset().create("min2", "Minimum");
    model.result().dataset("min2").set("data", "dset2");
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").set("data", "max2");
    model.result("pg12").set("titletype", "none");
    model.result("pg12").set("showgrid", false);
    model.result("pg12").set("legendpos", "upperleft");
    model.result("pg12").create("ptgr1", "PointGraph");
    model.result("pg12").feature("ptgr1").set("markerpos", "datapoints");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg12").feature("ptgr1").set("linewidth", "preference");
    model.result("pg12").feature("ptgr1").set("data", "max2");
    model.result("pg12").feature("ptgr1").set("expr", "Delta_Si*t");
    model.result("pg12").feature("ptgr1").set("unit", "nm");
    model.result("pg12").feature("ptgr1").set("descractive", true);
    model.result("pg12").feature("ptgr1").set("linestyle", "dashed");
    model.result("pg12").feature("ptgr1").set("linemarker", "point");
    model.result("pg12").feature("ptgr1").set("legend", true);
    model.result("pg12").feature("ptgr1").set("autopoint", false);
    model.result("pg12").feature("ptgr1").set("autosolution", false);
    model.result("pg12").feature("ptgr1").set("autoplotlabel", true);
    model.result("pg12").create("ptgr2", "PointGraph");
    model.result("pg12").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg12").feature("ptgr2").set("linewidth", "preference");
    model.result("pg12").feature("ptgr2").set("data", "min2");
    model.result("pg12").feature("ptgr2").set("expr", "Delta_Si*t");
    model.result("pg12").feature("ptgr2").set("unit", "nm");
    model.result("pg12").feature("ptgr2").set("descractive", true);
    model.result("pg12").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg12").feature("ptgr2").set("linemarker", "point");
    model.result("pg12").feature("ptgr2").set("legend", true);
    model.result("pg12").feature("ptgr2").set("autopoint", false);
    model.result("pg12").feature("ptgr2").set("autosolution", false);
    model.result("pg12").feature("ptgr2").set("autoplotlabel", true);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").selection().geom("geom1", 2);
    model.result().dataset("rev2").selection().named("sel1");
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("startangle", -90);
    model.result().dataset("rev3").set("revangle", 225);
    model.result().dataset("rev3").selection().geom("geom1", 2);
    model.result().dataset("rev3").selection().named("sel3");
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").run();
    model.result("pg13").setIndex("looplevel", 2, 0);
    model.result("pg13").set("titletype", "none");
    model.result("pg13").set("edges", false);
    model.result("pg13").set("showlegends", false);
    model.result("pg13").create("vol1", "Volume");
    model.result("pg13").feature("vol1").set("data", "rev2");
    model.result("pg13").feature("vol1").set("solutionparams", "parent");
    model.result("pg13").feature("vol1").set("expr", "cSiH4");
    model.result("pg13").feature("vol1").set("colortable", "Garnet");
    model.result("pg13").create("vol2", "Volume");
    model.result("pg13").feature("vol2").set("data", "rev3");
    model.result("pg13").feature("vol2").set("solutionparams", "parent");
    model.result("pg13").feature("vol2").set("expr", "1");
    model.result("pg13").feature("vol2").create("mtrl1", "MaterialAppearance");
    model.result("pg13").run();
    model.result("pg13").feature("vol2").feature("mtrl1").set("appearance", "custom");
    model.result("pg13").feature("vol2").feature("mtrl1").set("family", "steel");
    model.result("pg13").create("str1", "Streamline");
    model.result("pg13").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg13").feature("str1").set("posmethod", "uniform");
    model.result("pg13").feature("str1").set("udist", 0.05);
    model.result("pg13").feature("str1").set("linetype", "ribbon");
    model.result("pg13").feature("str1").create("col1", "Color");
    model.result("pg13").run();
    model.result("pg13").feature("str1").feature("col1").set("colortable", "Kyanite");
    model.result("pg13").run();

    model.view("view2").set("showgrid", false);

    model.result("pg13").run();

    model.title("\u7528\u4e8e\u4f4e\u538b\u5316\u5b66\u6c14\u76f8\u6c89\u79ef\u7684\u8239\u578b\u53cd\u5e94\u5668");

    model
         .description("\u5316\u5b66\u6c14\u76f8\u6c89\u79ef (CVD) \u662f\u5fae\u82af\u7247\u5236\u9020\u8fc7\u7a0b\u4e2d\u7684\u4e00\u4e2a\u91cd\u8981\u6b65\u9aa4\u3002\u4e00\u4e2a\u5e38\u89c1\u7684\u5e94\u7528\u662f\u5728\u4f4e\u538b\u53cd\u5e94\u5668\u4e2d\u5c06\u7845\u6c89\u79ef\u5728\u6676\u5706\u4e0a\uff0c\u4ee5\u83b7\u5f97\u5747\u5300\u7684\u6c89\u79ef\u539a\u5ea6\u3002\n\n\u672c\u4f8b\u6a21\u62df\u4f4e\u538b\u8239\u578b\u53cd\u5e94\u5668\u4e2d\u53cd\u5e94\u52a8\u529b\u5b66\u3001\u6d41\u4f53\u6d41\u52a8\u548c\u8d28\u91cf\u4f20\u9012\u7684\u8026\u5408\u73b0\u8c61\u3002\u901a\u8fc7\u4eff\u771f\u7814\u7a76\u4e86\u5728\u4e0d\u540c\u7684\u5de5\u4f5c\u6761\u4ef6\uff08\u5982\u6e29\u5ea6\u548c\u538b\u529b\uff09\u4e0b\uff0c\u53cd\u5e94\u5668\u4e2d\u7845\u7684\u6c89\u79ef\u901f\u7387\u548c\u539a\u5ea6\u7684\u53d8\u5316\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("boat_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
