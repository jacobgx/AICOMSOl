/*
 * flow_duct_impedance.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class flow_duct_impedance {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Aeroacoustics_and_Noise");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cpf", "CompressiblePotentialFlow", "geom1");
    model.component("comp1").physics().create("lpfbm", "LinearizedPotentialFlowBoundaryMode", "geom1");
    model.component("comp1").physics("lpfbm").field("velocitypotential").field("phi_sp");
    model.component("comp1").physics().create("lpfbm2", "LinearizedPotentialFlowBoundaryMode", "geom1");
    model.component("comp1").physics("lpfbm2").field("velocitypotential").field("phi_tp");
    model.component("comp1").physics().create("lpff", "LinearizedPotentialFlowFrequency", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cpf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/lpfbm", true);
    model.study("std1").feature("stat").setSolveFor("/physics/lpfbm2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/lpff", true);

    model.baseSystem("none");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("gamma", "1.4", "\u6bd4\u70ed\u7387");
    model.param().set("M", "-0.5", "\u5e73\u5747\u6d41\u52a8\u9a6c\u8d6b\u6570");
    model.param().set("m", "10", "\u65b9\u4f4d\u89d2\u6a21\u6570");
    model.param().set("omega", "16", "\u89d2\u9891\u7387");
    model.param().set("f", "omega/(2*pi)", "\u9891\u7387");
    model.param().set("rho0", "1", "\u53c2\u8003\u5bc6\u5ea6");
    model.param().set("C0", "1", "\u53c2\u8003\u5e73\u5747\u58f0\u901f");
    model.param().set("A", "0.01", "\u58f0\u6e90\u5f3a\u5ea6");
    model.param().set("Zw", "2-i", "\u5bfc\u7ba1\u58c1\u963b\u6297");
    model.param().set("zi", "1.86393", "\u8f74\u5411\u5750\u6807\uff0c\u5165\u53e3\u5e73\u9762");
    model.param().set("ri", "0.91705", "\u5f84\u5411\u5750\u6807\uff0c\u5165\u53e3\u5e73\u9762");
    model.param().set("k0max_abs", "omega/(C0-abs(M))", "\u6700\u5927\u7edd\u5bf9\u6ce2\u6570");

    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1")
         .set("coord", new String[]{"1-0.18453*s^2+0.10158*(exp(-11*(1-s))-exp(-11))/(1-exp(-11))", ""});
    model.component("comp1").geom("geom1").feature("pc1").setIndex("coord", "s*zi", 1);
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("pc2", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc2").set("parmax", 0.7);
    model.component("comp1").geom("geom1").feature("pc2")
         .set("coord", new String[]{"0.64212-sqrt(0.04777+0.98234*s^2)", ""});
    model.component("comp1").geom("geom1").feature("pc2").setIndex("coord", "s*zi", 1);
    model.component("comp1").geom("geom1").run("pc2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"0", "zi"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ls1", "pc2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("uni1", 5);
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex2").set("pc1", 1);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex1").set("uni1", 4);
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex2").set("pc1", 2);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1, 3);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("del1", "ls2", "ls3", "pc1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"ri", "1"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "zi"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(1);
    model.component("comp1").variable("var1").set("Mz", "-cpf.Vz");
    model.component("comp1").variable("var1").descr("Mz", "\u8f74\u5411\u9a6c\u8d6b\u6570");
    model.component("comp1").variable("var1").set("pabsn", "abs(lpff.p)/comp1.maxop1(lpff.p)");
    model.component("comp1").variable("var1").descr("pabsn", "\u5f52\u4e00\u5316\u538b\u529b");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6e90\u5e73\u9762");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(5);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5165\u53e3\u5e73\u9762");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7ec8\u7aef\u5e73\u9762");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(4);

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().set(1);
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_ip");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel2");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_sp");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().named("sel1");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "intop_tp");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop3").selection().named("sel3");

    model.component("comp1").physics("cpf").prop("ReferenceValues").set("pref", "cpf.rhoref^gamma/gamma");
    model.component("comp1").physics("cpf").prop("ReferenceValues").set("rhoref", "rho0");
    model.component("comp1").physics("cpf").prop("ReferenceValues").set("vref", "M");
    model.component("comp1").physics("cpf").feature("cpf1").set("gamma_mat", "userdef");
    model.component("comp1").physics("cpf").feature("cpf1").set("gamma", "gamma");
    model.component("comp1").physics("cpf").create("nf1", "NormalFlow", 1);
    model.component("comp1").physics("cpf").feature("nf1").selection().named("sel3");
    model.component("comp1").physics("cpf").create("mf1", "MassFlow", 1);
    model.component("comp1").physics("cpf").feature("mf1").selection().named("sel1");
    model.component("comp1").physics("lpfbm").selection().named("sel1");
    model.component("comp1").physics("lpfbm").prop("EquationSettings").set("m", "m");
    model.component("comp1").physics("lpfbm").feature("lpfm1").set("minput_velocity_src", "root.comp1.cpf.Vr");
    model.component("comp1").physics("lpfbm").feature("lpfm1").set("rho0_mat", "root.comp1.rho");
    model.component("comp1").physics("lpfbm").feature("lpfm1").set("c0_mat", "root.comp1.cpf.c");
    model.component("comp1").physics("lpfbm").create("imp1", "Impedance", 0);
    model.component("comp1").physics("lpfbm").feature("imp1").selection().set(7);
    model.component("comp1").physics("lpfbm").feature("imp1").set("Zn", "Zw");
    model.component("comp1").physics("lpfbm2").selection().named("sel3");
    model.component("comp1").physics("lpfbm2").prop("EquationSettings").set("m", "m");
    model.component("comp1").physics("lpfbm2").feature("lpfm1").set("minput_velocity_src", "root.comp1.cpf.Vr");
    model.component("comp1").physics("lpfbm2").feature("lpfm1").set("rho0_mat", "root.comp1.rho");
    model.component("comp1").physics("lpfbm2").feature("lpfm1").set("c0_mat", "root.comp1.cpf.c");
    model.component("comp1").physics("lpfbm2").create("imp1", "Impedance", 0);
    model.component("comp1").physics("lpfbm2").feature("imp1").selection().set(6);
    model.component("comp1").physics("lpfbm2").feature("imp1").set("Zn", "Zw");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(4, 7);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.015);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.001);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u80cc\u666f\u6d41");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "gamma", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "gamma", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "M", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,-0.05,-0.5)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u5e73\u5747\u6d41\u901f (cpf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "cpf.normV");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().set(1, 2);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u4e09\u7ef4\u5e73\u5747\u6d41\u901f (cpf)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "cpf.normV");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 0.8, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.8, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "zi", 1, 1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e73\u5747\u6d41\uff1arho \u548c Mz");
    model.result("pg3").set("data", "cln1");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("legendpos", "middleleft");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "rho");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "z");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("autodescr", true);
    model.result("pg3").feature("lngr1").set("legendprefix", "Ma = ");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("lngr2", "LineGraph");
    model.result("pg3").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr2").set("linewidth", "preference");
    model.result("pg3").feature("lngr2").set("expr", "Mz");
    model.result("pg3").feature("lngr2").set("xdata", "expr");
    model.result("pg3").feature("lngr2").set("xdataexpr", "z");
    model.result("pg3").feature("lngr2").set("legend", true);
    model.result("pg3").feature("lngr2").set("autodescr", true);
    model.result("pg3").feature("lngr2").set("legendprefix", "Ma = ");
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("mode", "ModeAnalysis");
    model.study("std2").feature("mode").set("plotgroup", "Default");
    model.study("std2").feature("mode").set("ftplistmethod", "manual");
    model.study("std2").feature("mode").set("modeFreq", "100[Hz]");
    model.study("std2").feature("mode").set("eigunit", "");
    model.study("std2").feature("mode").set("shiftactive", false);
    model.study("std2").feature("mode").set("chkeigregion", true);
    model.study("std2").feature("mode").set("storefact", false);
    model.study("std2").feature("mode").set("linpsolnum", "auto");
    model.study("std2").feature("mode").set("outputmap", new String[]{});
    model.study("std2").feature("mode").set("ngenAUX", "1");
    model.study("std2").feature("mode").set("goalngenAUX", "1");
    model.study("std2").feature("mode").set("ngenAUX", "1");
    model.study("std2").feature("mode").set("goalngenAUX", "1");
    model.study("std2").feature("mode").setSolveFor("/physics/cpf", false);
    model.study("std2").feature("mode").setSolveFor("/physics/lpfbm", true);
    model.study("std2").feature("mode").setSolveFor("/physics/lpfbm2", false);
    model.study("std2").feature("mode").setSolveFor("/physics/lpff", false);
    model.study("std2").label("\u7814\u7a76 2 - \u6e90\u5e73\u9762\u6a21\u5f0f");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "gamma", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "gamma", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "M", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0,-0.05,-0.5)", 0);
    model.study("std2").feature("mode").set("modeFreq", "f");
    model.study("std2").feature("mode").set("eigmethod", "region");
    model.study("std2").feature("mode").set("appnreigs", 10);
    model.study("std2").feature("mode").set("maxnreigs", 20);
    model.study("std2").feature("mode").set("eigsr", "-1.1*k0max_abs");
    model.study("std2").feature("mode").set("eiglr", "1.1*k0max_abs");
    model.study("std2").feature("mode").set("eigsi", -10);
    model.study("std2").feature("mode").set("eigli", 10);
    model.study("std2").feature("mode").set("usesol", true);
    model.study("std2").feature("mode").set("notsolmethod", "sol");
    model.study("std2").feature("mode").set("notstudy", "std1");
    model.study("std2").feature("mode").set("notsol", "sol1");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().dataset("rev2").set("data", "dset3");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u58f0\u538b (lpfbm)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").setIndex("looplevel", 11, 1);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").label("\u7ebf");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "lpfbm.p");
    model.result("pg4").feature("line1").set("colortable", "Wave");
    model.result("pg4").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u58f0\u538b\uff0c\u4e09\u7ef4 (lpfbm)");
    model.result("pg5").set("data", "rev2");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").setIndex("looplevel", 11, 1);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "lpfbm.p");
    model.result("pg5").feature("surf1").set("colortable", "Wave");
    model.result("pg5").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg4").run();
    model.result("pg4").label("\u6e90\u5e73\u9762\uff1a\u58f0\u538b\u548c\u8f74\u5411\u5f3a\u5ea6");
    model.result("pg4").selection().geom("geom1", 1);
    model.result("pg4").selection().named("sel1");
    model.result("pg4").set("applyselectiontodatasetedges", true);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"lpfbm.Ir", "lpfbm.Iz"});
    model.result("pg4").feature("arwl1").set("descr", "\u5f3a\u5ea6");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"0", "lpfbm.Iz"});
    model.result("pg4").feature("arwl1").set("arrowcount", 30);
    model.result("pg4").feature("arwl1").set("arrowlength", "logarithmic");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().dataset("rev2").set("hasspacevars", true);
    model.result().dataset("rev2").set("layermethod", "fine");
    model.result("pg5").run();
    model.result("pg5").label("\u6e90\u5e73\u9762\uff1a\u58f0\u538b\uff0c\u4e09\u7ef4 (lpfbm)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("data", "rev1");
    model.result("pg5").feature("surf2").set("expr", "1");
    model.result("pg5").feature("surf2").set("coloring", "uniform");
    model.result("pg5").feature("surf2").set("color", "gray");
    model.result("pg5").feature("surf2").create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().set(1, 2);
    model.result("pg5").feature("surf2").feature("sel1").set("evalstartcap", false);
    model.result("pg5").feature("surf2").feature("sel1").set("evalendcap", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").create("tran1", "Transparency");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("tran1").set("transparency", 0.2);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "lpfbm.p*exp(-i*m*rev2phi)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6e90\u5e73\u9762\uff1a\u632f\u578b\uff0cM = 0");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevelinput", "first", 1);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().named("sel1");
    model.result("pg6").feature("lngr1").set("expr", "phi_sp");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "r");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg6").feature("lngr1")
         .set("legendpattern", "kz = eval(lpfbm.kz)\uff0cz-dir = eval(if(intop_sp(lpfbm.Iz)>0,1,-1))");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6e90\u5e73\u9762\uff1a\u632f\u578b\uff0cM = -0.5");
    model.result("pg7").setIndex("looplevelinput", "last", 1);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u6e90\u5e73\u9762\uff1a\u6ce2\u6570 (real,imag)");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "real(kz)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "imag(kz)");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "imag(lpfbm.kn)", 0);
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "real(lpfbm.kn)");
    model.result("pg8").feature("glob1").set("linestyle", "none");
    model.result("pg8").feature("glob1").set("linemarker", "point");
    model.result("pg8").feature("glob1").create("filt1", "GlobalFilter");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").feature("filt1").set("expr", "intop_sp(lpfbm.Iz)>0");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("glob2", "glob1");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg8").feature("glob2").set("linemarker", "circle");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").feature("filt1").set("expr", "intop_sp(lpfbm.Iz)<0");
    model.result("pg8").run();

    model.study().create("std3");
    model.study("std3").create("mode", "ModeAnalysis");
    model.study("std3").feature("mode").set("plotgroup", "Default");
    model.study("std3").feature("mode").set("ftplistmethod", "manual");
    model.study("std3").feature("mode").set("modeFreq", "100[Hz]");
    model.study("std3").feature("mode").set("eigunit", "");
    model.study("std3").feature("mode").set("shiftactive", false);
    model.study("std3").feature("mode").set("chkeigregion", true);
    model.study("std3").feature("mode").set("storefact", false);
    model.study("std3").feature("mode").set("linpsolnum", "auto");
    model.study("std3").feature("mode").set("outputmap", new String[]{});
    model.study("std3").feature("mode").set("ngenAUX", "1");
    model.study("std3").feature("mode").set("goalngenAUX", "1");
    model.study("std3").feature("mode").set("ngenAUX", "1");
    model.study("std3").feature("mode").set("goalngenAUX", "1");
    model.study("std3").feature("mode").setSolveFor("/physics/cpf", false);
    model.study("std3").feature("mode").setSolveFor("/physics/lpfbm", false);
    model.study("std3").feature("mode").setSolveFor("/physics/lpfbm2", true);
    model.study("std3").feature("mode").setSolveFor("/physics/lpff", false);
    model.study("std3").label("\u7814\u7a76 3 - \u7ec8\u7aef\u5e73\u9762\u6a21\u5f0f");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "gamma", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("pname", "gamma", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("pname", "M", 0);
    model.study("std3").feature("param").setIndex("plistarr", "range(0,-0.05,-0.5)", 0);
    model.study("std3").feature("mode").set("modeFreq", "f");
    model.study("std3").feature("mode").set("eigmethod", "region");
    model.study("std3").feature("mode").set("appnreigs", 10);
    model.study("std3").feature("mode").set("maxnreigs", 20);
    model.study("std3").feature("mode").set("eigsr", "-1.1*k0max_abs");
    model.study("std3").feature("mode").set("eiglr", "1.1*k0max_abs");
    model.study("std3").feature("mode").set("eigsi", -10);
    model.study("std3").feature("mode").set("eigli", 10);
    model.study("std3").feature("mode").set("usesol", true);
    model.study("std3").feature("mode").set("notsolmethod", "sol");
    model.study("std3").feature("mode").set("notstudy", "std1");
    model.study("std3").feature("mode").set("notsol", "sol1");
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol16");
    model.sol("sol16").study("std3");
    model.sol("sol16").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol16");
    model.batch("p2").run("compute");

    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").label("\u4e8c\u7ef4\u65cb\u8f6c 2");
    model.result().dataset("rev3").set("data", "dset5");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u58f0\u538b (lpfbm2)");
    model.result("pg9").set("data", "dset5");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").setIndex("looplevel", 11, 1);
    model.result("pg9").set("dataisaxisym", "off");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").feature().create("line1", "Line");
    model.result("pg9").feature("line1").label("\u7ebf");
    model.result("pg9").feature("line1").set("showsolutionparams", "on");
    model.result("pg9").feature("line1").set("expr", "lpfbm2.p");
    model.result("pg9").feature("line1").set("colortable", "Wave");
    model.result("pg9").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg9").feature("line1").set("smooth", "internal");
    model.result("pg9").feature("line1").set("showsolutionparams", "on");
    model.result("pg9").feature("line1").set("data", "parent");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u58f0\u538b\uff0c\u4e09\u7ef4 (lpfbm2)");
    model.result("pg10").set("data", "rev3");
    model.result("pg10").setIndex("looplevel", 1, 0);
    model.result("pg10").setIndex("looplevel", 11, 1);
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u8868\u9762");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("expr", "lpfbm2.p");
    model.result("pg10").feature("surf1").set("colortable", "Wave");
    model.result("pg10").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg9").set("applyselectiontodatasetedges", false);
    model.result("pg9").run();
    model.result("pg9").label("\u7ec8\u7aef\u5e73\u9762\uff1a\u58f0\u538b\u548c\u8f74\u5411\u5f3a\u5ea6");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").selection().geom("geom1", 1);
    model.result("pg9").selection().named("sel3");
    model.result("pg9").set("applyselectiontodatasetedges", true);
    model.result("pg9").set("titletype", "label");
    model.result("pg9").create("arwl1", "ArrowLine");
    model.result("pg9").feature("arwl1").set("expr", new String[]{"lpfbm2.Ir", "lpfbm2.Iz"});
    model.result("pg9").feature("arwl1").set("descr", "\u5f3a\u5ea6");
    model.result("pg9").feature("arwl1").set("expr", new String[]{"0", "lpfbm2.Iz"});
    model.result("pg9").feature("arwl1").set("arrowcount", 30);
    model.result("pg9").feature("arwl1").set("arrowlength", "logarithmic");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().dataset("rev3").set("hasspacevars", true);
    model.result().dataset("rev3").set("layermethod", "fine");
    model.result("pg10").run();
    model.result("pg10").label("\u7ec8\u7aef\u5e73\u9762\uff1a\u58f0\u538b\uff0c\u4e09\u7ef4 (lpfbm2)");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").create("surf2", "Surface");
    model.result("pg10").feature("surf2").set("data", "rev1");
    model.result("pg10").feature("surf2").set("expr", "1");
    model.result("pg10").feature("surf2").set("coloring", "uniform");
    model.result("pg10").feature("surf2").set("color", "gray");
    model.result("pg10").feature("surf2").create("sel1", "Selection");
    model.result("pg10").feature("surf2").feature("sel1").selection().set(1, 2);
    model.result("pg10").feature("surf2").feature("sel1").set("evalstartcap", false);
    model.result("pg10").feature("surf2").feature("sel1").set("evalendcap", false);
    model.result("pg10").run();
    model.result("pg10").feature("surf2").create("tran1", "Transparency");
    model.result("pg10").run();
    model.result("pg10").feature("surf2").feature("tran1").set("transparency", 0.2);
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("expr", "lpfbm2.p*exp(-i*m*rev3phi)");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u7ec8\u7aef\u5e73\u9762\uff1a\u632f\u578b\uff0cM = 0");
    model.result("pg11").set("data", "dset5");
    model.result("pg11").setIndex("looplevelinput", "first", 1);
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr1").set("linewidth", "preference");
    model.result("pg11").feature("lngr1").selection().named("sel3");
    model.result("pg11").feature("lngr1").set("expr", "phi_tp");
    model.result("pg11").feature("lngr1").set("xdata", "expr");
    model.result("pg11").feature("lngr1").set("xdataexpr", "r");
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg11").feature("lngr1")
         .set("legendpattern", "kz = eval(lpfbm2.kz)\uff0cz-dir = eval(if(intop_tp(lpfbm2.Iz)>0,1,-1))");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("\u7ec8\u7aef\u5e73\u9762\uff1a\u632f\u578b\uff0cM = -0.5");
    model.result("pg12").setIndex("looplevelinput", "last", 1);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u7ec8\u7aef\u5e73\u9762\uff1a\u6ce2\u6570 (real,imag)");
    model.result("pg13").set("data", "dset5");
    model.result("pg13").set("titletype", "label");
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("xlabel", "real(kz)");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "imag(kz)");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").setIndex("expr", "imag(lpfbm2.kn)", 0);
    model.result("pg13").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg13").feature("glob1").set("xdata", "expr");
    model.result("pg13").feature("glob1").set("xdataexpr", "real(lpfbm2.kn)");
    model.result("pg13").feature("glob1").set("linestyle", "none");
    model.result("pg13").feature("glob1").set("linemarker", "point");
    model.result("pg13").feature("glob1").create("filt1", "GlobalFilter");
    model.result("pg13").run();
    model.result("pg13").feature("glob1").feature("filt1").set("expr", "intop_tp(lpfbm2.Iz)>0");
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("glob2", "glob1");
    model.result("pg13").run();
    model.result("pg13").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg13").feature("glob2").set("linemarker", "circle");
    model.result("pg13").run();
    model.result("pg13").feature("glob2").feature("filt1").set("expr", "intop_tp(lpfbm2.Iz)<0");
    model.result("pg13").run();

    model.component("comp1").physics("lpff").prop("EquationSettings").set("m", "m");
    model.component("comp1").physics("lpff").prop("PortSweepSettings").set("ModeShapeNormalization", "Power");

    model.component("comp1").multiphysics().create("pfc1", "BackgroundPotentialFlowCoupling", -1);

    model.component("comp1").physics("lpff").create("imp1", "Impedance", 1);
    model.component("comp1").physics("lpff").feature("imp1").selection().set(6, 8);
    model.component("comp1").physics("lpff").feature("imp1").set("Zn", "Zw");

    model.nodeGroup().create("grp1", "Physics", "lpff");
    model.nodeGroup("grp1").label("\u6e90\u5e73\u9762");

    model.component("comp1").physics("lpff").create("port1", "Port", 1);

    model.nodeGroup("grp1").add("port1");

    model.component("comp1").physics("lpff").feature("port1").selection().named("sel1");
    model.component("comp1").physics("lpff").feature("port1")
         .set("phi_o", "withsol('sol3',phi_sp,setval(M,-0.5),setind(lambda,5))");
    model.component("comp1").physics("lpff").feature("port1")
         .set("kn_o", "withsol('sol3',lpfbm.kn,setval(M,-0.5),setind(lambda,5))");
    model.component("comp1").physics("lpff").feature("port1").set("PortExcitation", "on");
    model.component("comp1").physics("lpff").feature("port1")
         .set("phi_i", "withsol('sol3',phi_sp,setval(M,-0.5),setind(lambda,1))");
    model.component("comp1").physics("lpff").feature("port1")
         .set("kn_i", "withsol('sol3',lpfbm.kn,setval(M,-0.5),setind(lambda,1))");
    model.component("comp1").physics("lpff").feature("port1").set("IncidentWave", "ModeScale");
    model.component("comp1").physics("lpff").feature("port1").set("Sin", 1);
    model.component("comp1").physics("lpff").create("port2", "Port", 1);

    model.nodeGroup("grp1").add("port2");

    model.component("comp1").physics("lpff").feature("port2").selection().named("sel1");
    model.component("comp1").physics("lpff").feature("port2")
         .set("phi_o", "withsol('sol3',phi_sp,setval(M,-0.5),setind(lambda,4))");
    model.component("comp1").physics("lpff").feature("port2")
         .set("kn_o", "withsol('sol3',lpfbm.kn,setval(M,-0.5),setind(lambda,4))");

    model.nodeGroup().create("grp2", "Physics", "lpff");
    model.nodeGroup("grp2").label("\u7ec8\u7aef\u5e73\u9762");

    model.component("comp1").physics("lpff").create("port3", "Port", 1);

    model.nodeGroup("grp2").add("port3");

    model.component("comp1").physics("lpff").feature("port3").selection().named("sel3");
    model.component("comp1").physics("lpff").feature("port3")
         .set("phi_o", "withsol('sol16',phi_tp,setval(M,-0.5),setind(lambda,3))");
    model.component("comp1").physics("lpff").feature("port3")
         .set("kn_o", "withsol('sol16',lpfbm2.kn,setval(M,-0.5),setind(lambda,3))");
    model.component("comp1").physics("lpff").create("port4", "Port", 1);

    model.nodeGroup("grp2").add("port4");

    model.component("comp1").physics("lpff").feature("port4").selection().named("sel3");
    model.component("comp1").physics("lpff").feature("port4")
         .set("phi_o", "withsol('sol16',phi_tp,setval(M,-0.5),setind(lambda,4))");
    model.component("comp1").physics("lpff").feature("port4")
         .set("kn_o", "withsol('sol16',lpfbm2.kn,setval(M,-0.5),setind(lambda,4))");

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/cpf", false);
    model.study("std4").feature("freq").setSolveFor("/physics/lpfbm", false);
    model.study("std4").feature("freq").setSolveFor("/physics/lpfbm2", false);
    model.study("std4").feature("freq").setSolveFor("/physics/lpff", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/pfc1", true);
    model.study("std4").label("\u7814\u7a76 4 - \u9891\u57df\uff08M = -0.5\uff0c\u5e26\u886c\u57ab\uff09");
    model.study("std4").feature("freq").set("plist", "f");
    model.study("std4").feature("freq").set("usesol", true);
    model.study("std4").feature("freq").set("notsolmethod", "sol");
    model.study("std4").feature("freq").set("notstudy", "std1");
    model.study("std4").feature("freq").set("notsolnum", "last");
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol28").runAll();

    model.result().create("pg14", "PlotGroup2D");
    model.result("pg14").set("applyselectiontodatasetedges", false);
    model.result("pg14").run();
    model.result("pg14").label("\u5f52\u4e00\u5316\u538b\u529b\uff1aM = -0.5\uff0c\u5e26\u886c\u57ab");
    model.result("pg14").set("data", "dset6");
    model.result("pg14").selection().geom("geom1", 2);
    model.result("pg14").selection().geom("geom1", 2);
    model.result("pg14").selection().set(1);
    model.result("pg14").set("applyselectiontodatasetedges", true);
    model.result("pg14").set("titletype", "label");
    model.result("pg14").create("con1", "Contour");
    model.result("pg14").feature("con1").set("expr", "pabsn");
    model.result("pg14").feature("con1").set("levelmethod", "levels");
    model.result("pg14").feature("con1")
         .set("levels", "0.0001 0.001 0.01 0.02 0.04 0.06 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9");
    model.result("pg14").feature("con1").set("contourtype", "filled");
    model.result("pg14").feature("con1").set("colorscalemode", "logarithmic");
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("con2", "con1");
    model.result("pg14").run();
    model.result("pg14").feature("con2").set("contourtype", "lines");
    model.result("pg14").feature("con2").set("coloring", "uniform");
    model.result("pg14").feature("con2").set("color", "black");
    model.result("pg14").feature("con2").set("colorlegend", false);
    model.result("pg14").run();
    model.result("pg14").run();
    model.result().create("pg15", "PlotGroup2D");
    model.result("pg15").set("applyselectiontodatasetedges", false);
    model.result("pg15").run();
    model.result("pg15").label("\u5f3a\u5ea6");
    model.result("pg15").set("data", "dset6");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", "lpff.I_mag");
    model.result("pg15").run();
    model.result("pg15").selection().geom("geom1", 2);
    model.result("pg15").selection().geom("geom1", 2);
    model.result("pg15").selection().set(1);
    model.result("pg15").set("applyselectiontodatasetedges", true);
    model.result("pg15").create("arws1", "ArrowSurface");
    model.result("pg15").feature("arws1").set("expr", new String[]{"lpff.Ir", "lpff.Iz"});
    model.result("pg15").feature("arws1").set("descr", "\u5f3a\u5ea6");
    model.result("pg15").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg15").feature("arws1").set("color", "white");
    model.result("pg15").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4\uff1a\u8870\u51cf");
    model.result().evaluationGroup("eg1").set("data", "none");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset6");
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "10*log10(lpff.port1.P_in/intop_ip(lpff.Iz))", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "M = -0.5\uff0c\u5e26\u886c\u57ab", 0);
    model.result().evaluationGroup("eg1").run();
    model.result("pg5").run();

    model.title("\u6d41\u7ba1 - \u5177\u6709\u963b\u6297\u6761\u4ef6\u7684\u6a21\u5f0f");

    model
         .description("\u672c\u4f8b\u5206\u6790\u4e00\u4e2a\u57fa\u4e8e\u6a21\u6001\u58f0\u4f20\u8f93\u3001\u5e26\u8f74\u5bf9\u79f0\u5185\u886c\u7684\u822a\u7a7a\u53d1\u52a8\u673a\u7ba1\u9053\u6a21\u578b\u4e2d\u7684\u58f0\u573a\u3002\u58f0\u6e90\u7531\u8fb9\u754c\u5904\u7684\u5355\u6a21\u6fc0\u53d1\u4ea7\u751f\uff0c\u901a\u8fc7\u7aef\u53e3\u8fb9\u754c\u6761\u4ef6\u5e94\u7528\u58f0\u6e90\u548c\u975e\u53cd\u5c04\u6761\u4ef6\u3002\u6a21\u578b\u5206\u6790\u5206\u4e09\u6b65\u8fdb\u884c\uff1a\u9996\u5148\u8ba1\u7b97\u80cc\u666f\u5e73\u5747\u6d41\uff08\u53ef\u538b\u7f29\u65e0\u65cb\u52bf\u6d41\uff09\uff0c\u7136\u540e\u901a\u8fc7\u8fb9\u754c\u6a21\u5f0f\u5206\u6790\u6765\u5206\u6790\u4f20\u64ad\u6a21\u5f0f\uff0c\u6700\u540e\u4f7f\u7528\u7ebf\u6027\u52bf\u6d41\u65b9\u7a0b\u6c42\u89e3\u5e26\u5185\u886c\u7684\u6d41\u7ba1\u4e2d\u7684\u58f0\u573a\u3002\u8be5\u6a21\u578b\u5bf9\u201c\u6d41\u7ba1\u201d\u6a21\u578b\u8fdb\u884c\u4e86\u6269\u5c55\uff0c\u5176\u4e2d\u8ba1\u7b97\u4e86\u7aef\u53e3\u5904\u4f7f\u7528\u7684\u6a21\u5f0f\uff0c\u5305\u62ec\u58c1\u886c\uff08\u963b\u6297\u6761\u4ef6\uff09\uff1b\u7ed3\u679c\u5c55\u793a\u4e86\u5e26\u6709\u80cc\u666f\u6d41\u548c\u5e26\u5185\u886c\u7ba1\u58c1\u7684\u60c5\u51b5\u3002");

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
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();

    model.label("flow_duct_impedance.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
