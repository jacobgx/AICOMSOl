/*
 * dam_break_column_ls.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class dam_break_column_ls {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Multiphase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ls", "LevelSet", "geom1");

    model.component("comp1").multiphysics().create("tpf1", "TwoPhaseFlowLevelSet", 3);
    model.component("comp1").multiphysics("tpf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("tpf1").set("Mathematics_physics", "ls");
    model.component("comp1").multiphysics("tpf1").selection().all();
    model.component("comp1").multiphysics().create("ww1", "WettedWall", 2);
    model.component("comp1").multiphysics("ww1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("ww1").set("Mathematics_physics", "ls");

    model.study().create("std1");
    model.study("std1").create("phasei", "PhaseInitialization");
    model.study("std1").feature("phasei").set("solnum", "auto");
    model.study("std1").feature("phasei").set("notsolnum", "auto");
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/ls", true);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/ww1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ww1", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{1.6, 0.61, 0.6});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.01, 0);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new double[]{0.4, 0.61, 0.3});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new double[]{0.12, 0.12, 0.6});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new double[]{0.9, 0.25, 0});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature().duplicate("blk4", "blk2");
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("blk3");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("dif2");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk4", "spl1(2)");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").camera().set("zoomanglefull", 16.5);
    model.component("comp1").view("view1").camera().setIndex("position", -3.6, 0);
    model.component("comp1").view("view1").camera().setIndex("position", "8.", 1);
    model.component("comp1").view("view1").camera().set("position", new String[]{"-3.6", "8.", "3.5"});
    model.component("comp1").view("view1").camera().setIndex("target", "0.80", 0);
    model.component("comp1").view("view1").camera().setIndex("target", "0.30", 1);
    model.component("comp1").view("view1").camera().set("target", new String[]{"0.80", "0.30", "0.30"});
    model.component("comp1").view("view1").camera().setIndex("up", "0.20", 0);
    model.component("comp1").view("view1").camera().setIndex("up", -0.25, 1);
    model.component("comp1").view("view1").camera().set("up", new String[]{"0.20", "-0.25", "0.95"});
    model.component("comp1").view("view1").camera().setIndex("rotationpoint", "0.80", 0);
    model.component("comp1").view("view1").camera().setIndex("rotationpoint", "0.30", 1);
    model.component("comp1").view("view1").camera().set("rotationpoint", new String[]{"0.80", "0.30", "0.30"});
    model.component("comp1").view("view1").camera().setIndex("viewoffset", 0, 0);
    model.component("comp1").view("view1").camera().set("viewoffset", new int[]{0, 0});

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").cpl("intop1").selection().set(15, 17, 21, 22);

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "Fp");
    model.component("comp1").probe("var1").set("expr", "intop1(p*spf.nxmesh)");
    model.component("comp1").probe("var1").set("descractive", true);

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
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
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
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup().create("NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Air");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", "");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("ki", "");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", "");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("ki", "");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", "(def.gamma+1)/2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat2").materialType("nonSolid");
    model.component("comp1").material("mat2").set("family", "air");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("useBNS", true);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(1);
    model.component("comp1").physics("ls").feature("lsm1").set("gamma", 10);
    model.component("comp1").physics("ls").feature("initfluid2").selection().set(2);

    model.component("comp1").multiphysics("tpf1").set("Fluid1", "mat1");
    model.component("comp1").multiphysics("tpf1").set("Fluid2", "mat2");
    model.component("comp1").multiphysics("ww1").selection().all();

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.05,1.7)");

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2);

    model.sol("sol1").study("std1");

    model.study("std1").feature("phasei").set("notlistsolnum", 1);
    model.study("std1").feature("phasei").set("notsolnum", "auto");
    model.study("std1").feature("phasei").set("listsolnum", 1);
    model.study("std1").feature("phasei").set("solnum", "auto");
    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "phasei");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "phasei");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u754c\u9762\u8ddd\u79bb (ls)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1").label("\u76f4\u63a5\uff0c\u754c\u9762\u8ddd\u79bb (ls)");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "i1");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").create("st2", "StudyStep");
    model.sol("sol1").feature("st2").set("study", "std1");
    model.sol("sol1").feature("st2").set("studystep", "time");
    model.sol("sol1").create("v2", "Variables");
    model.sol("sol1").feature("v2").feature("comp1_phils").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_phils").set("scaleval", "1");
    model.sol("sol1").feature("v2").set("initmethod", "sol");
    model.sol("sol1").feature("v2").set("initsol", "sol1");
    model.sol("sol1").feature("v2").set("initsoluse", "su1");
    model.sol("sol1").feature("v2").set("notsolmethod", "sol");
    model.sol("sol1").feature("v2").set("notsol", "sol1");
    model.sol("sol1").feature("v2").set("notsoluse", "su1");
    model.sol("sol1").feature("v2").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,1.7)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{"var1"});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.005);
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.05);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1")
         .set("atolmethod", new String[]{"comp1_GI", "global", "comp1_p", "scaled", "comp1_phils", "scaled", "comp1_u", "global"});
    model.sol("sol1").feature("t1")
         .set("atolvaluemethod", new String[]{"comp1_GI", "factor", "comp1_p", "factor", "comp1_phils", "factor", "comp1_u", "factor"});
    model.sol("sol1").feature("t1")
         .set("atolfactor", new String[]{"comp1_GI", "0.1", "comp1_p", "1", "comp1_phils", "0.01", "comp1_u", "0.1"});
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("estrat", "exclude");
    model.sol("sol1").feature("t1").set("rhoinf", 0.5);
    model.sol("sol1").feature("t1").set("predictor", "constant");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("stabcntrl", true);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", true);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.01");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("seDef", "Segregated");
    model.sol("sol1").feature("t1").create("se1", "Segregated");
    model.sol("sol1").feature("t1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("t1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_u", "comp1_p"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subdamp", 0.8);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subjtech", "once");
    model.sol("sol1").feature("t1").create("i1", "Iterative");
    model.sol("sol1").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("t1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("t1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("t1").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("t1").feature("i1").set("maxlinit", 100);
    model.sol("sol1").feature("t1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("t1").feature("i1")
         .label("\u5757\u7eb3\u7ef4-\u65af\u6258\u514b\u65af\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol1").feature("t1").feature("i1").create("bns1", "BlockNavierStokes");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").set("schurcomplementapproximation", "abssumf");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").set("velocityvars", new String[]{"comp1_u"});
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").set("pressurevars", new String[]{"comp1_p"});
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1")
         .set("prefun", "saamg");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1")
         .set("maxcoarsedof", 50000);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").set("strconn", 0.02);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1")
         .set("nullspace", "constant");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1")
         .set("usesmooth", false);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1")
         .set("saamgcompwise", true);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1")
         .set("compactaggregation", false);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("pr")
         .create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("pr")
         .feature("sl1").set("linesweeptype", "sor");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("pr")
         .feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("pr")
         .feature("sl1").set("linerelax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("pr")
         .feature("sl1").set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("pr")
         .feature("sl1").set("linemethod", "coupled");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("pr")
         .feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("pr")
         .feature("sl1").set("relax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("po")
         .create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("po")
         .feature("sl1").set("linesweeptype", "soru");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("po")
         .feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("po")
         .feature("sl1").set("linerelax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("po")
         .feature("sl1").set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("po")
         .feature("sl1").set("linemethod", "coupled");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("po")
         .feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("po")
         .feature("sl1").set("relax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("cs")
         .create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("cs")
         .feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("vs").feature("mg1").feature("cs")
         .feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1")
         .set("prefun", "saamg");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1")
         .set("maxcoarsedof", 50000);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").set("strconn", 0.02);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1")
         .set("nullspace", "constant");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1")
         .set("usesmooth", false);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1")
         .set("saamgcompwise", false);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1")
         .set("compactaggregation", false);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("pr")
         .create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("pr")
         .feature("sl1").set("linesweeptype", "sor");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("pr")
         .feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("pr")
         .feature("sl1").set("linerelax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("pr")
         .feature("sl1").set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("pr")
         .feature("sl1").set("linemethod", "uncoupled");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("pr")
         .feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("pr")
         .feature("sl1").set("relax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("po")
         .create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("po")
         .feature("sl1").set("linesweeptype", "soru");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("po")
         .feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("po")
         .feature("sl1").set("linerelax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("po")
         .feature("sl1").set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("po")
         .feature("sl1").set("linemethod", "uncoupled");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("po")
         .feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("po")
         .feature("sl1").set("relax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("cs")
         .create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("cs")
         .feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("i1").feature("bns1").feature("ps").feature("mg1").feature("cs")
         .feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("linsolver", "i1");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").label("\u901f\u5ea6 u\uff0c\u538b\u529b p");
    model.sol("sol1").feature("t1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_phils"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subdamp", 0.8);
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subjtech", "once");
    model.sol("sol1").feature("t1").create("i2", "Iterative");
    model.sol("sol1").feature("t1").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("t1").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("t1").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("t1").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("t1").feature("i2").set("maxlinit", 50);
    model.sol("sol1").feature("t1").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("t1").feature("i2").label("AMG\uff0c\u6c34\u5e73\u96c6\u53d8\u91cf (ls)");
    model.sol("sol1").feature("t1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("linsolver", "i2");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").label("\u6c34\u5e73\u96c6\u53d8\u91cf\uff0cphils");
    model.sol("sol1").feature("t1").feature("se1").set("ntolfact", 0.5);
    model.sol("sol1").feature("t1").feature("se1").set("segstabacc", "segaacc");
    model.sol("sol1").feature("t1").feature("se1").set("segaaccdim", 5);
    model.sol("sol1").feature("t1").feature("se1").set("segaaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("se1").set("segaaccdelay", 1);
    model.sol("sol1").feature("t1").feature("se1").set("maxsegiter", 10);
    model.sol("sol1").feature("t1").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d1")
         .label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol1").feature("t1").create("d2", "Direct");
    model.sol("sol1").feature("t1").feature("d2").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d2").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d2").label("\u76f4\u63a5\uff0c\u6c34\u5e73\u96c6\u53d8\u91cf (ls)");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").feature("t1").feature().remove("seDef");
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("solvertype", "solnum");
    model.sol("sol1").feature("v2").set("listsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("listsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("control", "time");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scaleval", 10);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", 1);

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
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
    model.result().dataset("surf1").selection().set();
    model.result().dataset("surf1").selection().inherit(false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u538b\u529b");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").feature().create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("expr", "ls.Vf1");
    model.result("pg3").feature("slc1").set("smooth", "internal");
    model.result("pg3").feature("slc1").set("data", "parent");
    model.result("pg3").feature().create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", "ls.Vf1");
    model.result("pg3").feature("iso1").set("levelmethod", "levels");
    model.result("pg3").feature("iso1").set("levels", "0.5");
    model.result("pg3").feature("iso1").set("coloring", "uniform");
    model.result("pg3").feature("iso1").set("colorlegend", false);
    model.result("pg3").feature("iso1").set("color", "gray");
    model.result("pg3").feature("iso1").set("smooth", "none");
    model.result("pg3").feature("iso1").set("data", "parent");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result("pg1").run();
    model.result("pg2").set("data", "surf1");
    model.result().setOnlyPlotWhenRequested(true);
    model.result("pg3").set("edges", false);
    model.result("pg3").feature().remove("slc1");
    model.result("pg3").feature("iso1").create("mtrl1", "MaterialAppearance");
    model.result("pg3").feature("iso1").create("tran1", "Transparency");
    model.result("pg3").feature("iso1").feature("tran1").set("transparency", 0.15);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "ls.Vf1");
    model.result("pg3").feature("surf1").set("descr", "\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg3").feature("surf1").set("rangedataactive", true);
    model.result("pg3").feature("surf1").set("rangedatamin", 0.5);
    model.result("pg3").feature("surf1").set("rangedatamax", 1);
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().all();
    model.result("pg3").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg3").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg3").feature("surf1").create("tran1", "Transparency");
    model.result("pg3").feature("surf1").feature("tran1").set("transparency", 0.15);
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").create("sel1", "Selection");
    model.result("pg3").feature("surf2").feature("sel1").selection()
         .set(2, 3, 5, 10, 11, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg3").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg3").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg3").feature("surf2").feature("mtrl1").set("family", "steelscratched");
    model.result("pg3").run();

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{6});
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{12});
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{20});
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{32});
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{36});
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{20});
    model.result("pg3").run();

    model.title("\u6e83\u575d\u5bf9\u67f1\u7684\u5f71\u54cd\uff08\u6c34\u5e73\u96c6\u65b9\u6cd5\uff09");

    model
         .description("\u8fd9\u4e2a\u77ac\u6001\u6a21\u578b\u4f7f\u7528\u201c\u5c42\u6d41\u4e24\u76f8\u6d41\uff0c\u6c34\u5e73\u96c6\u201d\u63a5\u53e3\u6765\u6a21\u62df\u6c34\u6ce2\u5bf9\u67f1\u7684\u51b2\u51fb\u3002\u95f8\u95e8\u540e\u9762\u6700\u521d\u6709\u4e00\u4e2a 0.3\u00a0\u7c73\u9ad8\u7684\u6c34\u4f53\u3002\u4eff\u771f\u5f00\u59cb\u65f6\uff0c\u95f8\u95e8\u7a81\u7136\u677e\u5f00\uff0c\u6c34\u4f53\u5f62\u6210\u5411\u7ed3\u6784\u79fb\u52a8\u7684\u6ce2\u6d6a\u3002\u5728\u51b2\u51fb\u7ed3\u6784\u540e\uff0c\u6c34\u7ee7\u7eed\u6d41\u52a8\uff0c\u76f4\u5230\u5728\u6c34\u7bb1\u58c1\u4e0a\u88ab\u53cd\u5c04\uff0c\u5e76\u518d\u6b21\u51b2\u51fb\u67f1\u4f53\u3002\u672c\u4f8b\u8ba1\u7b97\u67f1\u4e0a\u7684\u538b\u529b\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u5b9e\u9a8c\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("dam_break_column_ls.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
