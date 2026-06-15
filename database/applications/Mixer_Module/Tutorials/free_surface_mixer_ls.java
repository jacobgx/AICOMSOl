/*
 * free_surface_mixer_ls.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:14 by COMSOL 6.3.0.290. */
public class free_surface_mixer_ls {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");
    model.component("comp1").physics().create("ls", "LevelSet", "geom1");

    model.component("comp1").multiphysics().create("tpf1", "TwoPhaseFlowLevelSet", 3);
    model.component("comp1").multiphysics("tpf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("tpf1").set("Mathematics_physics", "ls");
    model.component("comp1").multiphysics("tpf1").selection().all();

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.study().create("std1");
    model.study("std1").create("phasei", "PhaseInitialization");
    model.study("std1").feature("phasei").set("solnum", "auto");
    model.study("std1").feature("phasei").set("notsolnum", "auto");
    model.study("std1").feature("phasei").set("outputmap", new String[]{});
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/ls", true);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tpf1", true);

    model.component("comp1").geom("geom1").insertFile("free_surface_mixer_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("mcf1");

    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").camera().set("zoomanglefull", 16.8);
    model.component("comp1").view("view1").camera().set("position", new String[]{"-3.5", "1.80", "3.1"});
    model.component("comp1").view("view1").camera().setIndex("target", "0.0", 0);
    model.component("comp1").view("view1").camera().setIndex("target", 0.35, 1);
    model.component("comp1").view("view1").camera().set("target", new String[]{"0.0", "0.35", "0.0"});
    model.component("comp1").view("view1").camera().set("up", new double[]{0.23, 0.95, -0.2});
    model.component("comp1").view("view1").camera().set("rotationpoint", new String[]{"0.0", "0.35", "0.0"});

    model.func().create("step1", "Step");
    model.func("step1").set("location", 2.5);
    model.func("step1").set("smooth", 5);

    model.component("comp1").common("rot1").selection().set(3);
    model.component("comp1").common("rot1").set("revolutionsPerTime", "275[rpm]*step1(t[1/s])");
    model.component("comp1").common("rot1").set("rotationAxis", new String[]{"0", "1", "0"});

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6240\u6709\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6e90\u548c\u76ee\u6807\u8fb9\u754c");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(2, 3, 30, 31);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u58c1");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel2"});
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u65cb\u8f6c\u57df\u7684\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("sel3").set(3);
    model.component("comp1").selection("sel3").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel3").set(3);
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u53f6\u8f6e\u58c1");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"dif1", "sel3"});

    model.component("comp1").material().create("mpmat1", "Multiphase");
    model.component("comp1").material("mpmat1").selection().geom("geom1", 3);
    model.component("comp1").material("mpmat1").selection().set(1, 2, 3);
    model.component("comp1").material("mpmat1").selection().inherit(false);
    model.component("comp1").material("mpmat1").set("vfDefinition", "ls");
    model.component("comp1").material("mpmat1").feature().create("phase2", "PhaseLink", "comp1");

    model.component("comp1").multiphysics("tpf1").set("multiphaseMaterialList", "mpmat1");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.material("mat1").label("Water, liquid");
    model.material("mat1").set("family", "water");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.material("mat1").propertyGroup("def").func("cs")
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
    model.material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an3").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mpmat1").feature("phase1").set("link", "mat1");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat2").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.material("mat2").propertyGroup().create("NonlinearModel", "Nonlinear model");
    model.material("mat2").propertyGroup().create("idealGas", "Ideal gas");
    model.material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.material("mat2").label("Air");
    model.material("mat2").set("family", "air");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.material("mat2").propertyGroup("def").func("rho").set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat2").propertyGroup("def").func("rho").set("argunit", new String[]{"Pa", "K"});
    model.material("mat2").propertyGroup("def").func("rho").set("plotaxis", new String[]{"off", "on"});
    model.material("mat2").propertyGroup("def").func("rho").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.material("mat2").propertyGroup("def").func("cs").set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("cs").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"Pa", "K"});
    model.material("mat2").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off", "on"});
    model.material("mat2").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"200"});
    model.material("mat2").propertyGroup("def").func("an2").set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat2").propertyGroup("def").set("molarmass", "");
    model.material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat2").propertyGroup("def").addInput("temperature");
    model.material("mat2").propertyGroup("def").addInput("pressure");
    model.material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897");
    model.material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.material("mat2").materialType("nonSolid");
    model.component("comp1").material("mpmat1").feature("phase2").set("link", "mat2");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("useBNS", true);
    model.component("comp1").physics("spf").feature("grav1").set("g", new String[]{"0", "-g_const", "0"});
    model.component("comp1").physics("spf").feature("init1")
         .set("p_init", "tpf1.rho2*g_const*(0.7[m]-y)*ls.Vf2+tpf1.rho1*g_const*(0.4667[m]-y)*ls.Vf1");
    model.component("comp1").physics("spf").feature("init1").set("CompensateForHydrostaticPressure", false);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(22);
    model.component("comp1").physics("spf").feature("prpc1").set("CompensateForHydrostaticPressure", false);
    model.component("comp1").physics("ls").feature("initfluid2").selection().set(2);

    model.component("comp1").multiphysics().create("ww1", "WettedWall", 2);
    model.component("comp1").multiphysics("ww1")
         .label("\u6da6\u6e7f\u58c1\uff0c\u81ea\u52a8\u6765\u81ea\u5750\u6807\u7cfb");
    model.component("comp1").multiphysics("ww1").selection().named("dif1");
    model.component("comp1").multiphysics("ww1").selection()
         .set(1, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57);
    model.component("comp1").multiphysics().create("ww2", "WettedWall", 2);
    model.component("comp1").multiphysics("ww2").label("\u6da6\u6e7f\u58c1\uff0c\u56fa\u5b9a\u58c1");
    model.component("comp1").multiphysics("ww2").selection().set(32);
    model.component("comp1").multiphysics("ww2").set("TranslationalVelocityOption", "ZeroFixedWall");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").run("size1");
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("int1");
    model.component("comp1").mesh("mesh1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(13, 18, 47, 52);
    model.component("comp1").mesh("mesh1").feature("size3").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.1,5.5)");

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3);

    model.sol("sol1").study("std1");
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

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").create("st2", "StudyStep");
    model.sol("sol1").feature("st2").set("study", "std1");
    model.sol("sol1").feature("st2").set("studystep", "time");
    model.sol("sol1").create("v2", "Variables");
    model.sol("sol1").feature("v2").feature("comp1_phils").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_ode1").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_phils").set("scaleval", "1");
    model.sol("sol1").feature("v2").feature("comp1_ode1").set("scaleval", "1");
    model.sol("sol1").feature("v2").set("initmethod", "sol");
    model.sol("sol1").feature("v2").set("initsol", "sol1");
    model.sol("sol1").feature("v2").set("initsoluse", "sol2");
    model.sol("sol1").feature("v2").set("notsolmethod", "sol");
    model.sol("sol1").feature("v2").set("notsol", "sol1");
    model.sol("sol1").feature("v2").set("notsoluse", "sol2");
    model.sol("sol1").feature("v2").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,5.5)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.005);
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.05);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1")
         .set("atolmethod", new String[]{"comp1_ep", "unscaled", "comp1_GI", "global", "comp1_k", "unscaled", "comp1_p", "scaled", "comp1_phils", "scaled", 
         "comp1_u", "global", "comp1_ode1", "global"});
    model.sol("sol1").feature("t1")
         .set("atol", new String[]{"comp1_ep", "0.09*sqrt((0.01*1)^3)/(0.035*0.4500000000000006)", "comp1_GI", "1e-3", "comp1_k", "(0.01*1)^2", "comp1_p", "1e-3", "comp1_phils", "1e-3", 
         "comp1_u", "1e-3", "comp1_ode1", "1e-3"});
    model.sol("sol1").feature("t1")
         .set("atolvaluemethod", new String[]{"comp1_ep", "manual", "comp1_GI", "factor", "comp1_k", "manual", "comp1_p", "factor", "comp1_phils", "factor", 
         "comp1_u", "factor", "comp1_ode1", "factor"});
    model.sol("sol1").feature("t1")
         .set("atolfactor", new String[]{"comp1_ep", "0.1", "comp1_GI", "0.1", "comp1_k", "0.1", "comp1_p", "1", "comp1_phils", "0.01", 
         "comp1_u", "0.1", "comp1_ode1", "0.1"});
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
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
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_ode1"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subdamp", 1);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subntolfact", 0.1);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subjtech", "onevery");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subtermconst", "iter");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subiter", 1);
    model.sol("sol1").feature("t1").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d1").label("\u76f4\u63a5\uff0c\u89d2\u4f4d\u79fb (spf)");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("linsolver", "d1");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").label("\u89d2\u4f4d\u79fb");
    model.sol("sol1").feature("t1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_u", "comp1_p"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subdamp", 0.8);
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subjtech", "once");
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
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("linsolver", "i1");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").label("\u901f\u5ea6 u\uff0c\u538b\u529b p");
    model.sol("sol1").feature("t1").feature("se1").create("ss3", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature("ss3").set("segvar", new String[]{"comp1_phils"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss3").set("subdamp", 0.8);
    model.sol("sol1").feature("t1").feature("se1").feature("ss3").set("subjtech", "once");
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
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
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
    model.sol("sol1").feature("t1").feature("se1").feature("ss3").set("linsolver", "i2");
    model.sol("sol1").feature("t1").feature("se1").feature("ss3").label("\u6c34\u5e73\u96c6\u53d8\u91cf\uff0cphils");
    model.sol("sol1").feature("t1").feature("se1").create("ss4", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("segvar", new String[]{"comp1_k", "comp1_ep"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("subdamp", 0.8);
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("subiter", 1);
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("subtermconst", "iter");
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("subjtech", "onevery");
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("subntolfact", 0.1);
    model.sol("sol1").feature("t1").create("i3", "Iterative");
    model.sol("sol1").feature("t1").feature("i3").set("linsolver", "gmres");
    model.sol("sol1").feature("t1").feature("i3").set("prefuntype", "left");
    model.sol("sol1").feature("t1").feature("i3").set("itrestart", 50);
    model.sol("sol1").feature("t1").feature("i3").set("rhob", 20);
    model.sol("sol1").feature("t1").feature("i3").set("maxlinit", 200);
    model.sol("sol1").feature("t1").feature("i3").set("nlinnormuse", "on");
    model.sol("sol1").feature("t1").feature("i3").label("AMG\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol1").feature("t1").feature("i3").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("pr").feature("sl1").set("iter", 0);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("i3").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("linsolver", "i3");
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").label("\u6e4d\u6d41\u53d8\u91cf");
    model.sol("sol1").feature("t1").feature("se1").set("ntolfact", 0.5);
    model.sol("sol1").feature("t1").feature("se1").set("maxsegiter", 10);
    model.sol("sol1").feature("t1").feature("se1").set("segstabacc", "segaacc");
    model.sol("sol1").feature("t1").feature("se1").set("segaaccdim", 5);
    model.sol("sol1").feature("t1").feature("se1").set("segaaccdelay", 0);
    model.sol("sol1").feature("t1").feature("se1").set("segaaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol1").feature("t1").feature("se1").feature("ll1").set("lowerlimit", "comp1.k 0 comp1.ep 0 ");
    model.sol("sol1").feature("t1").create("d2", "Direct");
    model.sol("sol1").feature("t1").feature("d2").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d2").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d2")
         .label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol1").feature("t1").create("d3", "Direct");
    model.sol("sol1").feature("t1").feature("d3").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d3").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d3").label("\u76f4\u63a5\uff0c\u6c34\u5e73\u96c6\u53d8\u91cf (ls)");
    model.sol("sol1").feature("t1").create("d4", "Direct");
    model.sol("sol1").feature("t1").feature("d4").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d4").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d4").label("\u76f4\u63a5\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").feature("t1").feature().remove("seDef");
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("solvertype", "solnum");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scaleval", 10);
    model.sol("sol1").feature("t1").set("consistent", false);
    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 56, 0);
    model.result("pg1").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond2/pg1");
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
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 56, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 56, 0);
    model.result("pg2").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond2/pcond1/pcond1/pg4");
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
    model.result("pg3").setIndex("looplevel", 56, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").setIndex("looplevel", 56, 0);
    model.result("pg3").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond2/pcond1/pcond1/pg3");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 56, 0);
    model.result("pg4").set("defaultPlotID", "StandaloneTwoPhaseFlowPhysicsInterfaces/icom1/pdef1/pcond2/pg1");
    model.result("pg4").feature().create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("expr", "ls.Vf1");
    model.result("pg4").feature("slc1").set("smooth", "internal");
    model.result("pg4").feature("slc1").set("data", "parent");
    model.result("pg4").feature().create("iso1", "Isosurface");
    model.result("pg4").feature("iso1").set("expr", "ls.Vf1");
    model.result("pg4").feature("iso1").set("levelmethod", "levels");
    model.result("pg4").feature("iso1").set("levels", "0.5");
    model.result("pg4").feature("iso1").set("coloring", "uniform");
    model.result("pg4").feature("iso1").set("colorlegend", false);
    model.result("pg4").feature("iso1").set("color", "gray");
    model.result("pg4").feature("iso1").set("smooth", "none");
    model.result("pg4").feature("iso1").set("data", "parent");
    model.result("pg1").run();
    model.result().setOnlyPlotWhenRequested(true);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6c34\u81ea\u7531\u8868\u9762");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").set("descriptionintitle", false);
    model.result("pg5").set("unitintitle", false);
    model.result("pg5").set("edges", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().named("dif1");
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 4, 5, 6, 7, 8, 10, 11, 13, 14, 15, 17, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57);
    model.result("pg5").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg5").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg5").feature("surf1").feature("mtrl1").set("family", "ironscratched");
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("expr", "ls.Vf1");
    model.result("pg5").feature("surf2").set("descr", "\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg5").feature("surf2").set("rangedataactive", true);
    model.result("pg5").feature("surf2").set("rangedatamin", 0.5);
    model.result("pg5").feature("surf2").set("rangedatamax", 1);
    model.result("pg5").feature("surf2").create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().set(12, 16, 18);
    model.result("pg5").feature("surf2").create("mtrl1", "MaterialAppearance");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg5").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg5").feature("surf2").feature("mtrl1").set("family", "water");
    model.result("pg5").feature("surf2").create("tran1", "Transparency");
    model.result("pg5").create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("expr", "ls.Vf1");
    model.result("pg5").feature("iso1").set("number", 1);
    model.result("pg5").feature("iso1").create("mtrl1", "MaterialAppearance");
    model.result("pg5").feature("iso1").feature("mtrl1").set("appearance", "custom");
    model.result("pg5").feature("iso1").feature("mtrl1").set("family", "water");
    model.result("pg5").feature("iso1").create("tran1", "Transparency");
    model.result("pg5").feature("iso1").feature("tran1").set("transparency", 0.15);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 31, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 41, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 44, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 46, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 51, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 56, 0);
    model.result("pg5").run();

    model.title("\u6405\u62cc\u5668\u4e2d\u7684\u81ea\u7531\u6db2\u9762\u6c34\u5e73\u96c6");

    model
         .description("\u672c\u4f8b\u4ecb\u7ecd\u4e00\u4e2a\u5e26\u6709\u4e09\u53f6\u53f6\u8f6e\u5e76\u90e8\u5206\u7528\u6321\u677f\u906e\u6321\u7684\u6e4d\u6d41\u6405\u62cc\u5668\u4e2d\u7684\u6e4d\u6d41\uff0c\u6f14\u793a\u4e86\u5982\u4f55\u901a\u8fc7\u4f7f\u7528\u201c\u6405\u62cc\u5668\u6a21\u5757\u201d\u7684\u201c\u6c34\u5e73\u96c6\u201d\u65b9\u6cd5\u6765\u8bbe\u7f6e\u201c\u65cb\u8f6c\u673a\u68b0\uff0c\u6e4d\u6d41\u201d\u63a5\u53e3\uff0c\u4ee5\u5206\u6790\u65cb\u8f6c\u51e0\u4f55\u4e2d\u7684\u81ea\u7531\u8868\u9762\u6d41\u52a8\u3002\u901a\u8fc7\u6267\u884c\u77ac\u6001\u4eff\u771f\uff0c\u53ef\u4ee5\u89c2\u5bdf\u5230\u65cb\u8f6c\u6d41\u4f53\u4ea7\u751f\u7684\u6da1\u6d41\u3002\n\n\u6ce8\uff1a\u77ac\u6001\u7814\u7a76\u53ef\u80fd\u9700\u8981\u5927\u7ea6\u4e00\u5929\u7684\u65f6\u95f4\u8fdb\u884c\u6c42\u89e3\uff0c\u5e76\u751f\u6210\u5927\u7ea6 3\u00a0GB \u7684\u6570\u636e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("free_surface_mixer_ls.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
