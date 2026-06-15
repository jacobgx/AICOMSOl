/*
 * pinched_flow_fractionation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:54 by COMSOL 6.3.0.290. */
public class pinched_flow_fractionation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("V1", "25[ul/h]");
    model.param().descr("V1", "\u5165\u53e3 1 \u7684\u6d41\u7387");
    model.param().set("V2", "1200[ul/h]");
    model.param().descr("V2", "\u5165\u53e3 2 \u7684\u6d41\u7387");
    model.param().set("rho_p", "1300[kg/m^3]");
    model.param().descr("rho_p", "\u9897\u7c92\u5bc6\u5ea6");
    model.param().set("d_min", "1.5[um]");
    model.param().descr("d_min", "\u6700\u5c0f\u7c92\u5f84");
    model.param().set("d_max", "11[um]");
    model.param().descr("d_max", "\u6700\u5927\u7c92\u5f84");

    model.component("comp1").geom("geom1").insertFile("pinched_flow_fractionation_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("dphi", "pi[rad]/Nb");
    model.component("comp1").variable("var1").descr("dphi", "\u76f8\u90bb\u652f\u8def\u7684\u5939\u89d2");
    model.component("comp1").variable("var1").set("phi0", "dphi/2");
    model.component("comp1").variable("var1").descr("phi0", "\u534a\u89d2");
    model.component("comp1").variable("var1").set("dx", "x-Cr");
    model.component("comp1").variable("var1")
         .descr("dx", "\u6cbf x \u8f74\u5230\u65cb\u8f6c\u4e2d\u5fc3\u7684\u8ddd\u79bb");
    model.component("comp1").variable("var1").set("phi", "atan2(dx,y)");
    model.component("comp1").variable("var1")
         .descr("phi", "\u56f4\u7ed5\u65cb\u8f6c\u4e2d\u5fc3\u7684\u6b63 y \u8f74\u7684\u89d2\u5ea6");
    model.component("comp1").variable("var1").set("exit", "floor((phi-phi0)/dphi)+1");
    model.component("comp1").variable("var1")
         .descr("exit", "\u987a\u65f6\u9488\u65b9\u5411\u7684\u652f\u8def\u7d22\u5f15");

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

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(3, 5, 6);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("FullyDevelopedFlowOption", "V0");
    model.component("comp1").physics("spf").feature("inl1").set("V0fdf", "V1");
    model.component("comp1").physics("spf").feature("inl1").set("Dzfdf", "Wi");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl2").selection().set(1);
    model.component("comp1").physics("spf").feature("inl2").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl2").set("FullyDevelopedFlowOption", "V0");
    model.component("comp1").physics("spf").feature("inl2").set("V0fdf", "V2");
    model.component("comp1").physics("spf").feature("inl2").set("Dzfdf", "Wi");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection()
         .set(15, 19, 28, 29, 46, 47, 48, 49, 50, 51, 52, 53, 54);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size2", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(11);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "0.1[um]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
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
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("mdist", new double[]{0.001, 0.01});
    model.result("pg1").run();

    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/fpt", true);

    model.component("comp1").physics("fpt").prop("ParticleSizeDistribution")
         .setIndex("ParticleSizeDistribution", "SpecifyParticleDiameter", 0);
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop", "rho_p");
    model.component("comp1").physics("fpt").create("inl1", "Inlet", 1);
    model.component("comp1").physics("fpt").feature("inl1").selection().set(5);
    model.component("comp1").physics("fpt").feature("inl1").setIndex("rt", "range(0,0.05,5)", 0);
    model.component("comp1").physics("fpt").feature("inl1").set("InitialPosition", "RandomPosition");
    model.component("comp1").physics("fpt").feature("inl1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("inl1").set("DpDistributionFunction", "Uniform");
    model.component("comp1").physics("fpt").feature("inl1").set("SamplingFromDistribution_dp0", "Random");
    model.component("comp1").physics("fpt").feature("inl1").set("dpmin", "d_min");
    model.component("comp1").physics("fpt").feature("inl1").set("dpmax", "d_max");
    model.component("comp1").physics("fpt").create("df1", "DragForce", 2);
    model.component("comp1").physics("fpt").feature("df1").selection().all();
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").create("lf1", "LiftForce", 2);
    model.component("comp1").physics("fpt").feature("lf1").selection().set(2);
    model.component("comp1").physics("fpt").feature("lf1").set("LiftLaw", "WallInduced");
    model.component("comp1").physics("fpt").feature("lf1").selection("ParallelBoundary1").set(13);
    model.component("comp1").physics("fpt").feature("lf1").selection("ParallelBoundary2").set(12);
    model.component("comp1").physics("fpt").feature("lf1").set("u_src", "root.comp1.u");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.001,5)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "part1");
    model.result("pg3").setIndex("looplevel", 5001, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("traj1").set("linetype", "line");
    model.result("pg3").run();
    model.result("pg3").feature("traj1").feature("col1").set("expr", "fpt.dp");
    model.result("pg3").feature("traj1").feature("col1").set("colortable", "Plasma");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "dset1");
    model.result("pg3").feature("surf1").set("colortable", "GrayPrint");
    model.result("pg3").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg3").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").run();
    model.result().export("anim1").set("maxframes", 100);
    model.result().export("anim1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u4e00\u7ef4\u76f4\u65b9\u56fe");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").create("hist1", "Histogram");
    model.result("pg4").feature("hist1").set("markerpos", "datapoints");
    model.result("pg4").feature("hist1").set("linewidth", "preference");
    model.result("pg4").feature("hist1").set("expr", "exit");
    model.result("pg4").feature("hist1").set("number", 5);
    model.result("pg4").feature("hist1").set("function", "discrete");
    model.result("pg4").feature("hist1").create("filt1", "HistogramFilter");
    model.result("pg4").run();
    model.result("pg4").feature("hist1").feature("filt1").set("expr", "x>Pb");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u4e8c\u7ef4\u76f4\u65b9\u56fe");
    model.result("pg5").set("data", "part1");
    model.result("pg5").create("hist1", "Histogram");
    model.result("pg5").feature("hist1").set("xexpr", "exit");
    model.result("pg5").feature("hist1").set("yexpr", "fpt.dp");
    model.result("pg5").feature("hist1").set("yunit", "\u00b5m");
    model.result("pg5").feature("hist1").set("xnumber", 5);
    model.result("pg5").feature("hist1").set("ynumber", 40);
    model.result("pg5").feature("hist1").set("function", "discrete");
    model.result("pg5").feature("hist1").set("colortable", "Prism");
    model.result("pg5").feature("hist1").create("filt1", "HistogramFilter");
    model.result("pg5").run();
    model.result("pg5").feature("hist1").feature("filt1").set("expr", "x>Pb");
    model.result("pg5").run();

    model.title("\u6324\u538b\u6d41\u5206\u79bb");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4f7f\u7528\u6324\u538b\u6d41\u5206\u79bb\u65b9\u6cd5\u5b9e\u73b0\u57fa\u4e8e\u5fae\u901a\u9053\u5c3a\u5bf8\u7684\u9897\u7c92\u5206\u79bb\u3002\u8fd9\u4e2a\u5fae\u88c5\u7f6e\u6709\u4e24\u4e2a\u5165\u53e3\u548c\u591a\u4e2a\u51fa\u53e3\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u5c42\u6d41\u201d\u63a5\u53e3\u8ba1\u7b97\u6db2\u4f53\u6d41\u52a8\u7684\u901f\u5ea6\u573a\uff1b\u7136\u540e\u4f7f\u7528\u201c\u6d41\u4f53\u6d41\u52a8\u9897\u7c92\u8ddf\u8e2a\u201d\u63a5\u53e3\u8ba1\u7b97\u6ce8\u5165\u9897\u7c92\u7684\u8fd0\u52a8\u8f68\u8ff9\u3002\u672c\u4f8b\u4f7f\u7528\u76f4\u65b9\u56fe\u6765\u8ddf\u8e2a\u57fa\u4e8e\u5927\u5c0f\u7684\u9897\u7c92\u5206\u79bb\uff0c\u5e76\u91cf\u5316\u4ece\u6bcf\u4e2a\u51fa\u53e3\u6d41\u51fa\u7684\u9897\u7c92\u5927\u5c0f\u8303\u56f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pinched_flow_fractionation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
