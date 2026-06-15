/*
 * pipe_elbow_erosion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:55 by COMSOL 6.3.0.290. */
public class pipe_elbow_erosion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowkomega", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L1", "0.5[m]", "\u957f\u5ea6\uff0c\u7ba1 1");
    model.param().set("L2", "1.5[m]", "\u957f\u5ea6\uff0c\u7ba1 2");
    model.param().set("r1", "0.1[m]", "\u534a\u5f84\uff0c\u7ba1 1");
    model.param().set("r2", "r1", "\u534a\u5f84\uff0c\u7ba1 2");
    model.param().set("R3", "0.5[m]", "\u5927\u534a\u5f84\uff0c\u5f2f\u5934");
    model.param().set("r3", "r1", "\u5c0f\u534a\u5f84\uff0c\u5f2f\u5934");
    model.param().set("fc", "0.1", "\u7406\u60f3\u5316\u5207\u524a\u7279\u6027\u4e0b\u7684\u9897\u7c92\u6bd4\u4f8b");
    model.param().set("kappa", "2", "\u5782\u76f4/\u6c34\u5e73\u529b\u7684\u6bd4\u7387");
    model.param().set("Hs", "1.96[GPa]", "\u786c\u5ea6\uff0c\u94c1");
    model.param().set("rho", "7860[kg/m^3]", "\u5bc6\u5ea6\uff0c\u94c1");
    model.param().set("sigma", "2850[kg/m^3]", "\u5bc6\u5ea6\uff0cSiC");
    model.param().set("Vi", "16.5[m/s]", "\u5165\u53e3\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r1");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L1");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("tor1", "Torus");
    model.component("comp1").geom("geom1").feature("tor1").set("rmaj", "R3");
    model.component("comp1").geom("geom1").feature("tor1").set("rmin", "r3");
    model.component("comp1").geom("geom1").feature("tor1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("tor1").set("pos", new String[]{"0", "R3", "L1"});
    model.component("comp1").geom("geom1").feature("tor1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("tor1").set("rot", 180);
    model.component("comp1").geom("geom1").run("tor1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "r2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L2");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "R3", "L1+R3"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("cyl1", 3);
    model.component("comp1").geom("geom1").feature("wp1").set("workplane3d", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 0.05);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2", "tor1", "wp1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1, 2, 3);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(3, 7);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Vi");
    model.component("comp1").physics("spf").prop("TurbulenceModelProperty").set("WallTreatment", "WallFunctions");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(14);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(1, 4, 10);

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

    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 2.2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 0.4);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "r3/10");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(3, 7);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(3, 12);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 40);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 80);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("elemcount", 70);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").run();

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
    model.result().dataset("surf1").selection().set(2, 6, 8, 9, 12, 13);
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
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickplane", "zx");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("linetype", "ribbon");
    model.result("pg1").feature("str1").selection().set(3, 7);
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/fpt", false);

    model.component("comp1").physics("fpt").prop("ParticleReleaseSpecification")
         .setIndex("ParticleReleaseSpecification", "SpecifyMassFlowRate", 0);
    model.component("comp1").physics("fpt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("fpt").feature("inl1").set("mdot", "0.6[kg/h]");
    model.component("comp1").physics("fpt").feature("inl1").set("InitialPosition", "Density");
    model.component("comp1").physics("fpt").feature("inl1").setIndex("N", 5000, 0);
    model.component("comp1").physics("fpt").feature("inl1").set("dpro", "spf.U");
    model.component("comp1").physics("fpt").feature("inl1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("inl1").selection().set(7);
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop", "sigma");
    model.component("comp1").physics("fpt").feature("pp1").set("dp", "1.7E-4[m]");
    model.component("comp1").physics("fpt").feature("wall1").create("ero1", "Erosion", 2);
    model.component("comp1").physics("fpt").feature("wall1").feature("ero1").set("ci", "fc");
    model.component("comp1").physics("fpt").feature("wall1").feature("ero1").set("Khv", "kappa");
    model.component("comp1").physics("fpt").feature("wall1").feature("ero1").set("Hv", "Hs");
    model.component("comp1").physics("fpt").feature("wall1").feature("ero1").set("rhosurf", "rho");
    model.component("comp1").physics("fpt").feature("wall1").create("ero2", "Erosion", 2);
    model.component("comp1").physics("fpt").feature("wall1").feature("ero2").set("ErosionModel", "DNV");
    model.component("comp1").physics("fpt").feature("wall1").create("ero3", "Erosion", 2);
    model.component("comp1").physics("fpt").feature("wall1").feature("ero3").set("ErosionModel", "ECRC");
    model.component("comp1").physics("fpt").create("df1", "DragForce", 3);
    model.component("comp1").physics("fpt").feature("df1").selection().all();
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("df1").set("TurbulentDispersionModel", "DiscreteRandomWalk");
    model.component("comp1").physics("fpt").feature("df1").set("k_src", "root.comp1.k");
    model.component("comp1").physics("fpt").feature("df1").set("ep_src", "root.comp1.spf.ep");
    model.component("comp1").physics("fpt").create("wall2", "Wall", 2);
    model.component("comp1").physics("fpt").feature("wall2").selection().set(3, 14);
    model.component("comp1").physics("fpt").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("fpt").feature("sym1").selection().set(1, 4, 10);
    model.component("comp1").physics("fpt").create("out1", "Outlet", 2);
    model.component("comp1").physics("fpt").feature("out1").selection().set(14);
    model.component("comp1").physics("fpt").feature("out1").set("WallCondition", "Disappear");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std2").feature("time").set("tlist", "range(0,5e-3,0.12)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1e-2");
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevel", 25, 0);
    model.result("pg4").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg4").create("traj1", "ParticleTrajectories");
    model.result("pg4").feature("traj1").set("pointtype", "point");
    model.result("pg4").feature("traj1").set("linetype", "none");
    model.result("pg4").feature("traj1").create("col1", "Color");
    model.result("pg4").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "part1");
    model.result("pg5").setIndex("looplevel", 25, 0);
    model.result("pg5").label("\u7d2f\u79ef\u53d8\u91cf (fpt)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "fpt.wall1.ero1.EM");
    model.result("pg5").feature("surf1").set("resolution", "norefine");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("traj1").feature("col1").set("expr", "fpt.phii");
    model.result("pg4").feature("traj1").feature("col1").set("descr", "\u5165\u5c04\u9510\u89d2");
    model.result("pg4").feature("traj1").feature("col1").set("unit", "\u00b0");
    model.result("pg4").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "dset2");
    model.result("pg5").run();
    model.result("pg5").label("\u51b2\u8680\u901f\u7387\uff0cFinnie");
    model.result("pg5").set("data", "mir1");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("smooth", "everywhere");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u51b2\u8680\u901f\u7387\uff0cDNV");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "fpt.wall1.ero2.EM");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u51b2\u8680\u901f\u7387\uff0cE/CRC");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "fpt.wall1.ero3.EM");
    model.result("pg7").run();

    model.title("\u6c61\u67d3\u7269\u9897\u7c92\u5f15\u8d77\u7684\u7ba1\u9053\u51b2\u8680");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u6d41\u4f53\u6d41\u52a8\u9897\u7c92\u8ddf\u8e2a\u201d\u63a5\u53e3\u5bf9\u7ba1\u9053\u5f2f\u5934\u4e2d\u7684\u51b2\u8680\u73b0\u8c61\u8fdb\u884c\u5efa\u6a21\uff0c\u4f7f\u7528\u591a\u4e2a\u4e0d\u540c\u7684\u51b2\u8680\u6a21\u578b\u6bd4\u8f83\u4e86\u7ba1\u9053\u5f2f\u5934\u5904\u7684\u51b2\u8680\u78e8\u635f\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pipe_elbow_erosion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
