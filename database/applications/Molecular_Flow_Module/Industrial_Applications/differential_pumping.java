/*
 * differential_pumping.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:17 by COMSOL 6.3.0.290. */
public class differential_pumping {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Industrial_Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);

    model.component("comp1").geom("geom1").insertFile("differential_pumping_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel2");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Dtube", "3[mm]", "Tube diameter");
    model.param().set("Ltube", "30[mm]", "Tube length");
    model.param().set("T0", "300[K]", "Temperature of system");
    model.param().set("Mn0", "0.04[kg/mol]", "Molar mass");
    model.param().set("mu0", "22.9e-6[Pa*s]", "Viscosity of gas");
    model.param().set("pa_inf", "1e-4[torr]", "Reservoir pressure (low vacuum)");
    model.param().set("alpha", "1", "Tube accommodation coefficient");
    model.param().set("Spump", "500[l/s]", "Pump speed");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(16);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("pb_inf", "abs(aveop1(fmf.N_G))*k_B_const*T0", "Reservoir pressure (high vacuum)");
    model.component("comp1").variable("var1")
         .set("qa_inf", "pa_inf+6*plambda", "Reservoir mass conductance (low vacuum)");
    model.component("comp1").variable("var1")
         .set("qb_inf", "pb_inf+6*plambda", "Reservoir mass conductance (high vacuum)");
    model.component("comp1").variable("var1")
         .set("omegabar", "(2-alpha)/alpha*((1+b1*alpha)+(epsilon*b0-(1+b1*alpha))*(b2*plambda)/(pa_inf-pb_inf)*log((pa_inf+b2*plambda)/(pb_inf+b2*plambda)))", "Dimensionless function");
    model.component("comp1").variable("var1")
         .set("F", "Fc*(1+(16*plambda)/(qa_inf+qb_inf)*(omegabar-3/4))", "Dimensionless function");
    model.component("comp1").variable("var1").set("Fc", "3*pi*Dtube/(32*Ltube)", "Dimensionless function");
    model.component("comp1").variable("var1")
         .set("qa", "(((1+F)*qa_inf^2+F*qb_inf^2)/(1+2*F))^0.5", "Inlet mass conductance (low vacuum side)");
    model.component("comp1").variable("var1")
         .set("qb", "(((1+F)*qb_inf^2+F*qa_inf^2)/(1+2*F))^0.5", "Outlet mass conductance (high vacuum side)");
    model.component("comp1").variable("var1").set("pa", "qa-6*plambda", "Inlet pressure (low vacuum side)");
    model.component("comp1").variable("var1").set("pb", "qb-6*plambda", "Outlet pressure (high vacuum side)");
    model.component("comp1").variable("var1").set("Mdot", "beta*F*(qa^2-qb^2)", "General mass flow rate");
    model.component("comp1").variable("var1").set("pm", "(pb+pa)/2", "Average pressure");
    model.component("comp1").variable("var1").set("Knm", "plambda/pm", "Average Knudsen number");
    model.component("comp1").variable("var1")
         .set("rho_b", "Mn0*pb_inf/(R_const*T0)", "Average density in reservoir b");
    model.component("comp1").variable("var1").set("lambda_b", "2*mu0/(c*rho_b)", "Mean free path in reservoir b");
    model.component("comp1").variable("var1").set("Knm_b", "lambda_b/0.5[m]", "Knudsen number in reservoir b");
    model.component("comp1").variable("var1")
         .set("c", "sqrt((8*R_const*T0)/(pi*Mn0))", "Molecular mean thermal speed");
    model.component("comp1").variable("var1").set("b0", "16/(3*pi)", "Dimensionless parameter");
    model.component("comp1").variable("var1").set("b1", "0.15", "Dimensionless parameter");
    model.component("comp1").variable("var1").set("b2", "0.7*alpha/(2-alpha)", "Dimensionless parameter");
    model.component("comp1").variable("var1").set("delta", "4/3*(2-alpha)", "Dimensionless parameter");
    model.component("comp1").variable("var1")
         .set("kappa", "(delta-1)/delta*alpha*Ltube/Dtube", "Dimensionless parameter");
    model.component("comp1").variable("var1").set("epsilon", "(1+kappa)/(delta+kappa)", "Dimensionless parameter");
    model.component("comp1").variable("var1")
         .set("plambda", "pi*mu0*c/(4*Dtube)", "Pressure at which the Knudsen number is unity");
    model.component("comp1").variable("var1").set("beta", "Dtube^3/(3*pi*mu0*c^2)", "Mass flow rate coefficient");
    model.component("comp1").variable("var1")
         .set("omegabar_f", "(2-alpha)/alpha*epsilon*b0", "Free molecular flow limit for omegabar");
    model.component("comp1").variable("var1")
         .set("Mdot_c_inf", "Dtube^4*pm*(pa_inf-pb_inf)/(16*mu0*c^2*Ltube)", "Mass flow rate, continuum limit, infinite tube");
    model.component("comp1").variable("var1")
         .set("Mdot_f_inf", "Mdot_c_inf*8*plambda/pm*omegabar_f", "Mass flow rate, free molecular flow limit, infinite tube");
    model.component("comp1").variable("var1")
         .set("Mdot_inf", "Mdot_c_inf*(1+8*plambda/pm*omegabar)", "Mass flow rate, infinite tube");

    model.component("comp1").physics("fmf").selection().named("geom1_sel1");
    model.component("comp1").physics("fmf").prop("Compute").set("ComputeP", false);
    model.component("comp1").physics("fmf").prop("IntegrationProperty").set("IntegrationResolution", 1024);
    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_G", "Mn0", 0);
    model.component("comp1").physics("fmf").feature("st1").set("T", "T0");
    model.component("comp1").physics("fmf").create("pmp1", "VacuumPump", 2);
    model.component("comp1").physics("fmf").feature("pmp1").selection().named("geom1_sel5");
    model.component("comp1").physics("fmf").feature("pmp1").set("SpecifyPump", "PumpSpeed");
    model.component("comp1").physics("fmf").feature("pmp1").setIndex("pspeed", "Spump/2", 0);
    model.component("comp1").physics("fmf").create("wall2", "Wall", 2);
    model.component("comp1").physics("fmf").feature("wall2").selection().named("geom1_sel2");
    model.component("comp1").physics("fmf").feature("wall2").set("BCType", "OutgassingWall");
    model.component("comp1").physics("fmf").feature("wall2").set("BoundaryCondition", "TotalMassFlow");
    model.component("comp1").physics("fmf").feature("wall2").setIndex("tmf", "Mdot/2", 0);
    model.component("comp1").physics("fmf").create("msym1", "Symmetry", -1);
    model.component("comp1").physics("fmf").feature("msym1").selection("FirstReflectionPlane").set(17);

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_sel2");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_adjsel1");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Dtube", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Dtube", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "pa_inf", 0);
    model.study("std1").feature("stat")
         .setIndex("plistarr", "1e-1[torr] 5e-2[torr] 3e-2[torr] 2e-2[torr] 1e-2[torr] 5e-3[torr] 1e-3[torr] 1e-4[torr]", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u603b\u6570\u5bc6\u5ea6 (fmf)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "fmf.ntot");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().named("geom1_difsel2");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7ba1\u8d28\u91cf\u6d41\u7387");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "Mdot", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u603b\u8d28\u91cf\u6d41\u7387", 0);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "1/Knm");
    model.result("pg3").feature("glob1").set("linemarker", "circle");
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("ylog", true);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5f52\u4e00\u5316\u6d41\u7387 (1)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "Mdot/Mdot_f_inf", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u8d28\u91cf\u6d41\u7387", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "Mdot_inf/Mdot_f_inf", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u8d28\u91cf\u6d41\u7387\uff0c\u65e0\u9650\u7ba1", 1);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "1/Knm");
    model.result("pg4").set("xlog", true);
    model.result("pg4").set("ylog", true);
    model.result("pg4").feature("glob1").set("linemarker", "diamond");
    model.result("pg4").run();
    model.result("pg4").label("\u5f52\u4e00\u5316\u8d28\u91cf\u6d41\u7387");
    model.result("pg4").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "Knm_b", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component("comp1").physics().create("fmf2", "FreeMolecularFlow", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/fmf2", false);
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/fmf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/fmf2", true);

    model.component("comp1").physics("fmf2").selection().set(2, 3);
    model.component("comp1").physics("fmf2").prop("Compute").set("ComputeP", false);
    model.component("comp1").physics("fmf2").prop("IntegrationProperty").set("IntegrationResolution", 2048);
    model.component("comp1").physics("fmf2").feature("fmfp1").setIndex("Mn_G2", "Mn0", 0);
    model.component("comp1").physics("fmf2").feature("st1").set("T", "T0");
    model.component("comp1").physics("fmf2").create("pmp1", "VacuumPump", 2);
    model.component("comp1").physics("fmf2").feature("pmp1").selection().named("geom1_sel5");
    model.component("comp1").physics("fmf2").feature("pmp1").set("SpecifyPump", "PumpSpeed");
    model.component("comp1").physics("fmf2").feature("pmp1").setIndex("pspeed", "Spump/2", 0);
    model.component("comp1").physics("fmf2").create("res1", "Reservoir", 2);
    model.component("comp1").physics("fmf2").feature("res1").selection().named("geom1_sel3");
    model.component("comp1").physics("fmf2").feature("res1").setIndex("p0", "pa_inf", 0);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_sel3");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().set();
    model.component("comp1").cpl("intop2").selection().named("geom1_sel5");

    model.component("comp1").physics("fmf2").create("msym1", "Symmetry", -1);
    model.component("comp1").physics("fmf2").feature("msym1").selection("FirstReflectionPlane").set(10, 17);

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("size1").selection().named("geom1_sel6");
    model.component("comp1").mesh("mesh2").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh2").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh2").create("size2", "Size");
    model.component("comp1").mesh("mesh2").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("size2").selection().named("geom1_sel3");
    model.component("comp1").mesh("mesh2").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ftri1").selection().named("geom1_sel3");
    model.component("comp1").mesh("mesh2").run("ftri1");
    model.component("comp1").mesh("mesh2").create("map1", "Map");
    model.component("comp1").mesh("mesh2").feature("map1").selection().named("geom1_sel4");
    model.component("comp1").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 80);
    model.component("comp1").mesh("mesh2").feature("map1").feature("dis1").selection().set(19);
    model.component("comp1").mesh("mesh2").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ftri2").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh2").create("ftri3", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ftri3").selection().set(10);
    model.component("comp1").mesh("mesh2").run();

    model.study("std2").feature("stat").setSolveFor("/physics/fmf", false);
    model.study("std2").feature("stat").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf2)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "fmf2.Gtot");
    model.result("pg5").feature("surf1").set("resolution", "norefine");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u603b\u6570\u5bc6\u5ea6 (fmf2)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "fmf2.ntot");
    model.result("pg6").feature("surf1").set("resolution", "norefine");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg5").run();
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("geom1_difsel2");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u4f4d\u7f6e (mm)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u6570\u5bc6\u5ea6 (1/m<SUP>3</SUP>)");
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").label("\u7ba1\u5185\u7684\u6570\u5bc6\u5ea6");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().set(19);
    model.result("pg7").feature("lngr1").set("expr", "fmf2.ntot");
    model.result("pg7").feature("lngr1").set("xdata", "reversedarc");
    model.result("pg7").feature("lngr1").set("resolution", "norefine");
    model.result("pg7").run();
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "pb/(k_B_const*T0)", 0);
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "0");
    model.result("pg7").feature("glob1").set("linestyle", "none");
    model.result("pg7").feature("glob1").set("linecolor", "black");
    model.result("pg7").feature("glob1").set("linemarker", "circle");
    model.result("pg7").run();
    model.result("pg7").create("glob2", "Global");
    model.result("pg7").feature("glob2").set("markerpos", "datapoints");
    model.result("pg7").feature("glob2").set("linewidth", "preference");
    model.result("pg7").feature("glob2").setIndex("expr", "pa/(k_B_const*T0)", 0);
    model.result("pg7").feature("glob2").set("xdata", "expr");
    model.result("pg7").feature("glob2").set("xdataexpr", "30[mm]");
    model.result("pg7").feature("glob2").set("linestyle", "none");
    model.result("pg7").feature("glob2").set("linecolor", "red");
    model.result("pg7").feature("glob2").set("linemarker", "circle");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("expr", "Mdot", 0);
    model.result().numerical("gev2").setIndex("descr", "\u6d41\u5165\u89e3\u6790\u89e3", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().duplicate("gev3", "gev2");
    model.result().numerical("gev3").setIndex("expr", "2*intop1(fmf2.Jnet_G2)*Mn0/N_A_const", 0);
    model.result().numerical("gev3").setIndex("descr", "\u6d41\u5165\u6570\u503c\u89e3", 0);
    model.result().numerical("gev3").set("table", "tbl2");
    model.result().numerical("gev3").appendResult();
    model.result().numerical().duplicate("gev4", "gev3");
    model.result().numerical("gev4").setIndex("expr", "2*intop2(fmf2.Jnet_G2)*Mn0/N_A_const", 0);
    model.result().numerical("gev4").setIndex("descr", "\u6d41\u51fa\u6570\u503c\u89e3", 0);
    model.result().numerical("gev4").set("table", "tbl2");
    model.result().numerical("gev4").appendResult();

    model.title("\u5dee\u52a8\u6cf5\u6d66");

    model
         .description("\u5dee\u52a8\u6cf5\u6d66\u771f\u7a7a\u7cfb\u7edf\u4f7f\u7528\u4e00\u4e2a\u5c0f\u5b54\u6216\u7ba1\u9053\u8fde\u63a5\u771f\u7a7a\u7cfb\u7edf\u4e2d\u4e24\u4e2a\u538b\u529b\u4e0d\u540c\u7684\u90e8\u5206\u3002\u8fd9\u7c7b\u7cfb\u7edf\u5e38\u7528\u4e8e\u5de5\u827a\u6d41\u7a0b\u8981\u6c42\u5728\u8f83\u9ad8\u538b\u4e0b\u8fd0\u884c\uff0c\u5e76\u901a\u8fc7\u4f7f\u7528 UHV \u7684\u68c0\u6d4b\u5668\u8fdb\u884c\u76d1\u63a7\u3002\u5728\u6b64\u6a21\u578b\u4e2d\uff0c\u4f7f\u7528\u6d41\u7387\u7684\u89e3\u6790\u8868\u8fbe\u5f0f\u5bf9\u6c14\u4f53\u901a\u8fc7\u72ed\u7a84\u7ba1\u9053\u6d41\u5165\u9ad8\u771f\u7a7a\u5ba4\u7684\u8fd0\u52a8\u4f5c\u8fd1\u4f3c\u5904\u7406\u3002\u4e5f\u53ef\u4ee5\u5bf9\u6b64\u6a21\u578b\u76f4\u63a5\u4f7f\u7528\u5b9e\u9a8c\u6570\u636e\u3002\u901a\u8fc7\u5c06\u89e3\u6790\u6a21\u578b\u8026\u5408\u5230\u201c\u81ea\u7531\u5206\u5b50\u6d41\u201d\u63a5\u53e3\u53ef\u83b7\u5f97\u4eff\u771f\u7ed3\u679c\uff0c\u5c06\u5176\u4e0e\u5305\u542b\u7ba1\u9053\u548c\u7cfb\u7edf UHV \u90e8\u5206\u7684\u81ea\u7531\u5206\u5b50\u6d41\u7684\u4eff\u771f\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("differential_pumping.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
