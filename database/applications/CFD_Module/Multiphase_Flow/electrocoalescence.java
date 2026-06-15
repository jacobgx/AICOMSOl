/*
 * electrocoalescence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class electrocoalescence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Multiphase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("pf", "PhaseField", "geom1");

    model.component("comp1").multiphysics().create("tpf1", "TwoPhaseFlowPhaseField", 2);
    model.component("comp1").multiphysics("tpf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("tpf1").set("Mathematics_physics", "pf");
    model.component("comp1").multiphysics("tpf1").selection().all();

    model.study().create("std1");
    model.study("std1").create("phasei", "PhaseInitialization");
    model.study("std1").feature("phasei").set("ftplistmethod", "manual");
    model.study("std1").feature("phasei").set("solnum", "auto");
    model.study("std1").feature("phasei").set("notsolnum", "auto");
    model.study("std1").feature("phasei").set("outputmap", new String[]{});
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").setSolveFor("/physics/es", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/pf", true);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/es", true);
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/pf", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tpf1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{30, 10});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 1.6);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{4, 6});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 1.2);
    model.component("comp1").geom("geom1").feature("c2").set("pos", new double[]{7, 3.5});

    model.param().set("perm_water", "80");
    model.param().descr("perm_water", "\u4ecb\u7535\u5e38\u6570\uff0c\u6c34");
    model.param().set("perm_oil", "2.2");
    model.param().descr("perm_oil", "\u4ecb\u7535\u5e38\u6570\uff0c\u6cb9");
    model.param().set("u_in", "50[mm/s]");
    model.param().descr("u_in", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6");
    model.param().set("u_max", "3/2*u_in");
    model.param().descr("u_max", "\u8fd1\u4f3c\u6700\u5927\u901f\u5ea6");
    model.param().set("sigma", "0.031[N/m]");
    model.param().descr("sigma", "\u8868\u9762\u5f20\u529b\u7cfb\u6570");
    model.param().set("V0", "5[kV]");
    model.param().descr("V0", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .set("Tem11", "-epsilon0_const*es.epsilonr_iso/2*(es.Ex^2+es.Ey^2)+epsilon0_const*es.epsilonr_iso*es.Ex^2");
    model.component("comp1").variable("var1")
         .descr("Tem11", "\u9ea6\u514b\u65af\u97e6\u5e94\u529b\u5f20\u91cf\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Tem22", "-epsilon0_const*es.epsilonr_iso/2*(es.Ex^2+es.Ey^2)+epsilon0_const*es.epsilonr_iso*es.Ey^2");
    model.component("comp1").variable("var1")
         .descr("Tem22", "\u9ea6\u514b\u65af\u97e6\u5e94\u529b\u5f20\u91cf\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1").set("Tem12", "epsilon0_const*es.epsilonr_iso*es.Ex*es.Ey");
    model.component("comp1").variable("var1")
         .descr("Tem12", "\u9ea6\u514b\u65af\u97e6\u5e94\u529b\u5f20\u91cf\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var1").set("Tem21", "epsilon0_const*es.epsilonr_iso*es.Ex*es.Ey");
    model.component("comp1").variable("var1")
         .descr("Tem21", "\u9ea6\u514b\u65af\u97e6\u5e94\u529b\u5f20\u91cf\uff0c21 \u5206\u91cf");
    model.component("comp1").variable("var1").set("Fx", "d(Tem11,x)+d(Tem12,y)");
    model.component("comp1").variable("var1").descr("Fx", "\u529b\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1").set("Fy", "d(Tem21,x)+d(Tem22,y)");
    model.component("comp1").variable("var1").descr("Fy", "\u529b\uff0cy \u5206\u91cf");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 0.005);
    model.component("comp1").func("step1").set("smooth", 0.01);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u51fa\u53e3");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(4);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5165\u53e3");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u6cb9-\u6c34\u754c\u9762");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(5, 6, 7, 8, 9, 10, 11, 12);

    model.component("comp1").physics("es").feature("init1").set("V", "y*V0/10[mm]");
    model.component("comp1").physics("es").create("ccnf1", "ChargeConservationFluid", 2);
    model.component("comp1").physics("es").feature("ccnf1").selection().all();
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().set(3);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0");
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(2);

    model.component("comp1").multiphysics("tpf1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").multiphysics("tpf1").set("sigma", "sigma");

    model.component("comp1").material().create("mpmat1", "Multiphase");
    model.component("comp1").material("mpmat1").selection().geom("geom1", 2);
    model.component("comp1").material("mpmat1").selection().set(1, 2, 3);
    model.component("comp1").material("mpmat1").selection().inherit(false);
    model.component("comp1").material("mpmat1").selection().embedded(false);
    model.component("comp1").material("mpmat1").set("vfDefinition", "pf");
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
    model.material("mat1").label("Water");
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
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic ");
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
    model.material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"perm_water"});
    model.material().create("mat2", "Common", "");
    model.component("comp1").material("mpmat1").feature("phase2").set("link", "mat2");
    model.material("mat2").label("\u6cb9");
    model.material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"perm_oil"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"884[kg/m^3]"});
    model.material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"0.474[Pa*s]"});

    model.component("comp1").physics("pf").feature("pfm1").set("epsilon_pf", "0.15[mm]");
    model.component("comp1").physics("pf").feature("pfm1").set("chiOption", "velocity");
    model.component("comp1").physics("pf").feature("pfm1").set("U", "u_max");
    model.component("comp1").physics("pf").feature("initfluid2").selection().set(1);
    model.component("comp1").physics("spf").create("vf1", "VolumeForce", 2);
    model.component("comp1").physics("spf").feature("vf1").selection().all();
    model.component("comp1").physics("spf").feature("vf1").set("F", new String[]{"Fx", "Fy", "0"});
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel2");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u_in*step1(t*1[1/s])");
    model.component("comp1").physics("pf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("pf").feature("inl1").selection().named("sel2");
    model.component("comp1").physics("pf").feature("inl1").set("pfcond", "Fluid2pf");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel1");
    model.component("comp1").physics("spf").feature("out1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("pf").create("out1", "Outlet", 1);
    model.component("comp1").physics("pf").feature("out1").selection().named("sel1");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.05,0.3)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v2").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_V").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_V").set("scaleval", "1e3");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scaleval", "u_max");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_u", "comp1_p", "comp1_spf_inl1_Pinlfdf", "comp1_spf_out1_Pinlfdf", "comp1_V"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").active(false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "es.normE");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.U");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (pf)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "pf.Vf1");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").set("expr", "pf.Vf1");
    model.result("pg5").feature("con1").set("levelmethod", "levels");
    model.result("pg5").feature("con1").set("levels", "0.5");
    model.result("pg5").feature("con1").set("coloring", "uniform");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result("pg5").feature("con1").set("color", "gray");
    model.result("pg5").feature("con1").set("smooth", "none");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").label("\u901f\u5ea6");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormax", "u_max");
    model.result("pg3").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").label("\u6d41\u4f53\u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg3").feature("surf2").set("expr", "tpf1.Vf1");
    model.result("pg3").feature("surf2").set("rangedataactive", true);
    model.result("pg3").feature("surf2").set("rangedatamin", 0.5);
    model.result("pg3").feature("surf2").set("rangedatamax", 1);
    model.result("pg3").feature("surf2").set("colortable", "Cividis");
    model.result("pg3").feature("surf2").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").label("\u7535\u52bf");
    model.result("pg3").feature("con1").set("contourtype", "tubes");
    model.result("pg3").feature("con1").set("radiusexpr", "0.06");
    model.result("pg3").feature("con1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 5, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 6, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 7, 0);
    model.result("pg3").run();

    model.title("\u7535\u805a\u7ed3\u5206\u79bb");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u7cfb\u7edf\u4e2d\u7684\u7535\u805a\u7ed3\u8fc7\u7a0b\uff0c\u5728\u8be5\u7cfb\u7edf\u4e2d\uff0c\u4e24\u4e2a\u6c34\u6ef4\u5728\u4e24\u4e2a\u5e26\u7535\u677f\u4e4b\u95f4\u7684\u5145\u6cb9\u901a\u9053\u4e2d\u4f20\u9012\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("electrocoalescence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
