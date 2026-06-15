/*
 * hydrocyclone.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class hydrocyclone {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Single-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowv2f", "geom1");

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("outputmap", new String[]{});
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("u_in", "5[m/s]");
    model.param().descr("u_in", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("r_in", "0.0725[m]");
    model.param().descr("r_in", "\u5165\u53e3\u534a\u5f84");
    model.param().set("r_out", "0.07[m]");
    model.param().descr("r_out", "\u5f03\u6599\u51fa\u53e3\u534a\u5f84");
    model.param().set("R_f", "0.05");
    model.param().descr("R_f", "\u5f03\u6599\u6d41\u4f53\u79ef\u5206\u6570");
    model.param().set("u_out", "R_f*2*(r_in/r_out)^2*u_in");
    model.param().descr("u_out", "\u5f03\u6599\u51fa\u53e3\u901f\u5ea6");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("filename", "hydrocyclone_mesh.mphbin");
    model.component("comp1").mesh("mesh1").feature("imp1").importData();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(9, 40);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "u_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(20, 21, 29, 30);
    model.component("comp1").physics("spf").feature("out1").set("BoundaryCondition", "Velocity");
    model.component("comp1").physics("spf").feature("out1").set("U0out", "u_out");
    model.component("comp1").physics("spf").create("out2", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out2").selection().set(18, 19, 28, 31);
    model.component("comp1").physics("spf").feature("out2").set("NormalFlow", true);
    model.component("comp1").physics("spf").feature("out2").set("SuppressBackflow", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 22, 23, 24, 25, 26, 27, 32, 33, 34, 35, 36, 37, 38, 39, 41, 42);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").selection()
         .set(2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 22, 23, 24, 25, 26, 27, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42);
    model.result().dataset().create("edg1", "Edge3D");
    model.result().dataset("edg1").selection()
         .set(1, 2, 4, 5, 7, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 21, 23, 24, 25, 27, 28, 29, 31, 32, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 47, 49, 55, 58, 59, 61, 63, 65, 66, 72, 73, 74, 75, 78, 80, 81, 82);
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset("cpl2").set("quickplane", "xz");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", -0.5, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.6, 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", 0.6, 1, 2);
    model.result("pg1").run();
    model.result("pg1").label("\u6d41\u7ebf");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature().remove("slc1");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "surf2");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("data", "edg1");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "custom");
    model.result("pg1").feature("line1")
         .set("customcolor", new double[]{0.501960813999176, 0.501960813999176, 0.501960813999176});
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("selnumber", 10);
    model.result("pg1").feature("str1").selection().set(18, 19, 28, 31);
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("radiusexpr", "0.0025");
    model.result("pg1").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("str1").set("color", "custom");
    model.result("pg1").feature("str1").set("customcolor", new double[]{0.501960813999176, 0, 0.250980406999588});
    model.result("pg1").run();
    model.result("pg1").create("str2", "Streamline");
    model.result("pg1").feature("str2").selection().set(20, 21, 29, 30);
    model.result("pg1").feature("str2").set("selnumber", 2);
    model.result("pg1").feature("str2").set("linetype", "tube");
    model.result("pg1").feature("str2").set("radiusexpr", "0.0025");
    model.result("pg1").feature("str2").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("str2").set("color", "custom");
    model.result("pg1").feature("str2").set("customcolor", new double[]{0, 0.501960813999176, 0.7529411911964417});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("data", "surf2");
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("data", "edg1");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "custom");
    model.result("pg2").feature("line1")
         .set("customcolor", new double[]{0.501960813999176, 0.501960813999176, 0.501960813999176});
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature().remove("tran1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "cpl1");
    model.result("pg2").feature("surf2").set("expr", "p");
    model.result("pg2").feature("surf2").set("rangecoloractive", true);
    model.result("pg2").feature("surf2").set("rangecolormin", -13000);
    model.result("pg2").feature("surf2").set("rangecolormax", 106000);
    model.result("pg2").feature("surf2").set("colortable", "JupiterAuroraBorealis");
    model.result("pg2").feature("surf2").set("colortabletrans", "reverse");
    model.result("pg2").run();
    model.result("pg2").create("surf3", "Surface");
    model.result("pg2").feature("surf3").set("data", "cpl2");
    model.result("pg2").feature("surf3").set("expr", "p");
    model.result("pg2").feature("surf3").set("rangecoloractive", true);
    model.result("pg2").feature("surf3").set("rangecolormin", -13000);
    model.result("pg2").feature("surf3").set("rangecolormax", 106000);
    model.result("pg2").feature("surf3").set("colorlegend", false);
    model.result("pg2").feature("surf3").set("colortable", "JupiterAuroraBorealis");
    model.result("pg2").feature("surf3").set("colortabletrans", "reverse");
    model.result("pg2").run();
    model.result("pg2").create("str1", "StreamlineSurface");
    model.result("pg2").feature("str1").set("data", "cpl1");
    model.result("pg2").feature("str1").set("expr", new String[]{"0", "v", "w"});
    model.result("pg2").feature("str1").set("posmethod", "magnitude");
    model.result("pg2").feature("str1").set("mdist", new String[]{"1/128", "1/64"});
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").set("radiusexpr", "0.002");
    model.result("pg2").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").run();
    model.result("pg2").create("str2", "StreamlineSurface");
    model.result("pg2").feature("str2").set("data", "cpl2");
    model.result("pg2").feature("str2").set("expr", new String[]{"u", "0", "w"});
    model.result("pg2").feature("str2").set("posmethod", "magnitude");
    model.result("pg2").feature("str2").set("mdist", new String[]{"1/128", "1/64"});
    model.result("pg2").feature("str2").set("linetype", "tube");
    model.result("pg2").feature("str2").set("radiusexpr", "0.002");
    model.result("pg2").feature("str2").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("str2").set("color", "gray");
    model.result("pg2").feature("str2").set("pointtype", "arrow");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u6da1\u6838");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("view", "view1");
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("data", "surf2");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").run();
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("data", "edg1");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "custom");
    model.result("pg4").feature("line1")
         .set("customcolor", new double[]{0.501960813999176, 0.501960813999176, 0.501960813999176});
    model.result("pg4").run();
    model.result("pg4").create("iso1", "Isosurface");
    model.result("pg4").feature("iso1").set("expr", "spf.vorticityz");
    model.result("pg4").feature("iso1").set("descr", "\u6da1\u91cf\u573a\uff0cz \u5206\u91cf");
    model.result("pg4").feature("iso1").set("levelmethod", "levels");
    model.result("pg4").feature("iso1").set("levels", 90);
    model.result("pg4").feature("iso1").set("interactive", true);
    model.result("pg4").feature("iso1").set("coloring", "uniform");
    model.result("pg4").feature("iso1").set("color", "custom");
    model.result("pg4").feature("iso1").set("customcolor", new double[]{0, 0.501960813999176, 0.7529411911964417});
    model.result("pg4").feature("iso1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u65cb\u6d41\u901f\u5ea6");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u65cb\u6d41\u901f\u5ea6");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "r (m)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u65cb\u6d41\u901f\u5ea6 (m/s)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").run();
    model.result("pg5").set("data", "cln1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "(x*v-y*u)/sqrt(x^2+y^2+eps)");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "-x");
    model.result("pg5").run();
    model.result("pg2").run();

    model.title("\u6c34\u529b\u65cb\u6d41\u5668\u4e2d\u7684\u6d41\u52a8");

    model
         .description("\u5728\u8be5\u6559\u5b66\u6a21\u578b\u4e2d\uff0c\u4f7f\u7528 v2-f \u6e4d\u6d41\u6a21\u578b\u6a21\u62df\u6c34\u529b\u65cb\u6d41\u5668\u4e2d\u7684\u5404\u5411\u5f02\u6027\u6e4d\u6d41\u3002\u590d\u6742\u7684\u65cb\u6da1\u53ef\u5206\u4e3a\u5916\u90e8\u534a\u81ea\u7531\u65cb\u6da1\u548c\u56fa\u4f53\u65cb\u8f6c\u7684\u5185\u90e8\u6da1\u6838\uff0c\u53ef\u4ee5\u901a\u8fc7\u5728\u58c1\u6cd5\u5411\u4e0a\u7684\u6e4d\u6d41\u6ce2\u52a8\u963b\u5c3c\u7ed3\u679c\u5f97\u5230\u9a8c\u8bc1\u3002\u4e24\u4e2a\u5165\u53e3\u4e0e\u4e24\u4e2a\u51fa\u53e3\u4e4b\u95f4\u7684\u901f\u5ea6\u573a\u548c\u538b\u964d\u4e0e\u6587\u732e\u4e2d\u7c7b\u4f3c\u8bbe\u8ba1\u5f97\u5230\u7684\u5b9a\u6027\u548c\u5b9a\u91cf\u7ed3\u679c\u5747\u4e00\u81f4\u3002");

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("hydrocyclone.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
