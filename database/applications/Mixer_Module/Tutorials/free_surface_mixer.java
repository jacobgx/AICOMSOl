/*
 * free_surface_mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:14 by COMSOL 6.3.0.290. */
public class free_surface_mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.study().create("std1");
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);
    model.study("std1").create("sfs", "StationaryFreeSurface");
    model.study("std1").feature("sfs").set("solnum", "auto");
    model.study("std1").feature("sfs").set("notsolnum", "auto");
    model.study("std1").feature("sfs").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").insertFile("free_surface_mixer_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("mcf1");

    model.component("comp1").view("view1").camera().set("zoomanglefull", 11.61);
    model.component("comp1").view("view1").camera().setIndex("position", 4.97, 0);
    model.component("comp1").view("view1").camera().setIndex("position", 1.79, 1);
    model.component("comp1").view("view1").camera().set("position", new double[]{4.97, 1.79, 1.779});
    model.component("comp1").view("view1").camera().setIndex("target", 7.15E-6, 0);
    model.component("comp1").view("view1").camera().set("target", new double[]{7.15E-6, 0.35, 0});
    model.component("comp1").view("view1").camera().setIndex("target", -4.4116E-6, 2);
    model.component("comp1").view("view1").camera().setIndex("up", -0.249, 0);
    model.component("comp1").view("view1").camera().setIndex("up", 0.965, 1);
    model.component("comp1").view("view1").camera().setIndex("up", -0.08475, 2);
    model.component("comp1").view("view1").camera().setIndex("rotationpoint", "0.0", 0);
    model.component("comp1").view("view1").camera().set("rotationpoint", new String[]{"0.0", "0.35", "0.0"});
    model.component("comp1").view("view1").camera().setIndex("viewoffset", -0.0395, 0);
    model.component("comp1").view("view1").camera().set("viewoffset", new double[]{-0.0395, -0.054});

    model.param().set("viscFact", "1");
    model.param().descr("viscFact", "Viscosity factor");

    model.func().create("step1", "Step");
    model.func("step1").set("location", 0.5);
    model.func("step1").set("ncontder", "1");

    model.component("comp1").common("rot1").selection().set(3);
    model.component("comp1").common("rot1").set("revolutionsPerTime", "100[rpm]");
    model.component("comp1").common("rot1").set("rotationAxis", new String[]{"0", "1", "0"});
    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().set(2);

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").feature("grav1").set("g", new String[]{"0", "-g_const", "0"});
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(32);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "ZeroFixedWall");
    model.component("comp1").physics("spf").create("sfs1", "StationaryFreeSurface", 2);
    model.component("comp1").physics("spf").feature("sfs1").selection().set(12);
    model.component("comp1").physics("spf").create("fs1", "FreeSurface", 2);
    model.component("comp1").physics("spf").feature("fs1").selection().set(12);

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
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"eta(T)*viscFact"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.008);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.12);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 0.004);
    model.component("comp1").mesh("mesh1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hgrad", 1.12);
    model.component("comp1").mesh("mesh1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(2, 3, 5, 21, 30, 31, 32);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.013);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurve", 0.35);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(34, 38, 56);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 16);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 1.6);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemcount", 12);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 2);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").create("rf1", "Reference");
    model.component("comp1").mesh("mesh2").feature("rf1").set("sequence", "mesh1");
    model.component("comp1").mesh("mesh2").feature("rf1").create("sca1", "Scale");
    model.component("comp1").mesh("mesh2").feature("rf1").feature("sca1").set("scale", 2);
    model.component("comp1").mesh("mesh2").feature("rf1").expand();
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", "0.009*2");
    model.component("comp1").mesh("mesh2").feature("size1").set("hmin", "0.004*2");
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh().create("mesh3");
    model.component("comp1").mesh("mesh3").create("rf1", "Reference");
    model.component("comp1").mesh("mesh3").feature("rf1").set("sequence", "mesh2");
    model.component("comp1").mesh("mesh3").feature("rf1").create("sca1", "Scale");
    model.component("comp1").mesh("mesh3").feature("rf1").feature("sca1").set("scale", 2);
    model.component("comp1").mesh("mesh3").feature("rf1").expand();
    model.component("comp1").mesh("mesh3").feature("size1").set("hmin", "0.004*4");
    model.component("comp1").mesh("mesh3").feature("size").set("hmin", "0.009*4");
    model.component("comp1").mesh("mesh3").run();

    model.study("std1").feature("frrot").set("useadvanceddisable", true);
    model.study("std1").feature("frrot").set("disabledcommon", new String[]{"free1"});
    model.study("std1").feature("frrot").setSolveFor("/common/free1", false);
    model.study("std1").feature("frrot").setEntry("mesh", "geom1", "mesh1");
    model.study("std1").feature("frrot").set("useparam", true);
    model.study("std1").feature("frrot").setIndex("pname", "C", 0);
    model.study("std1").feature("frrot").setIndex("plistarr", "", 0);
    model.study("std1").feature("frrot").setIndex("punit", "m", 0);
    model.study("std1").feature("frrot").setIndex("pname", "C", 0);
    model.study("std1").feature("frrot").setIndex("plistarr", "", 0);
    model.study("std1").feature("frrot").setIndex("punit", "m", 0);
    model.study("std1").feature("frrot").setIndex("pname", "viscFact", 0);
    model.study("std1").feature("frrot").setIndex("plistarr", "50 1", 0);
    model.study("std1").feature("frrot").set("pcontinuationmode", "no");
    model.study("std1").feature("frrot").set("preusesol", "yes");
    model.study("std1").feature("frrot").mglevel().create("mgl1");
    model.study("std1").feature("frrot").mglevel("mgl1").setEntry("mesh", "geom1", "mesh2");
    model.study("std1").feature("frrot").mglevel().create("mgl2");
    model.study("std1").feature("sfs").set("useadvanceddisable", true);
    model.study("std1").feature("sfs").set("disabledcommon", new String[]{"free1"});
    model.study("std1").feature("sfs").setSolveFor("/common/free1", false);
    model.study("std1").feature("sfs").setEntry("mesh", "geom1", "mesh1");

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

    model.study("std1").feature("frrot").set("notlistsolnum", 1);
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("listsolnum", 1);
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("sfs").set("notlistsolnum", 1);
    model.study("std1").feature("sfs").set("notsolnum", "auto");
    model.study("std1").feature("sfs").set("listsolnum", 1);
    model.study("std1").feature("sfs").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "frrot");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "frrot");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").create("p1", "Parametric");
    model.sol("sol1").feature("s1").feature().remove("pDef");
    model.sol("sol1").feature("s1").feature("p1").set("control", "frrot");
    model.sol("sol1").feature("s1").set("control", "frrot");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_u", "comp1_p"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subdamp", 0.5);
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf ()");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("gmglevels", 2);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("linsolver", "i1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").label("\u901f\u5ea6 u\uff0c\u538b\u529b p");
    model.sol("sol1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_k", "comp1_ep"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subdamp", 0.35);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subiter", 3);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subtermconst", "itertol");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subntolfact", 1);
    model.sol("sol1").feature("s1").create("i2", "Iterative");
    model.sol("sol1").feature("s1").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("s1").feature("i2").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i2").label("AMG\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol1").feature("s1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("iter", 0);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("linsolver", "i2");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").label("\u6e4d\u6d41\u53d8\u91cf");
    model.sol("sol1").feature("s1").feature("se1").set("segstabacc", "segcflcmp");
    model.sol("sol1").feature("s1").feature("se1").set("subinitcfl", 3);
    model.sol("sol1").feature("s1").feature("se1").set("subkppid", 0.65);
    model.sol("sol1").feature("s1").feature("se1").set("subkdpid", 0.05);
    model.sol("sol1").feature("s1").feature("se1").set("subkipid", 0.05);
    model.sol("sol1").feature("s1").feature("se1").set("subcfltol", 0.1);
    model.sol("sol1").feature("s1").feature("se1").set("maxsegiter", 400);
    model.sol("sol1").feature("s1").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol1").feature("s1").feature("se1").feature("ll1").set("lowerlimit", "comp1.k 0 comp1.ep 0 ");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1").label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf ()");
    model.sol("sol1").feature("s1").create("d2", "Direct");

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol1").feature("s1").feature("d2").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d2").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d2").label("\u76f4\u63a5\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").create("st2", "StudyStep");
    model.sol("sol1").feature("st2").set("study", "std1");
    model.sol("sol1").feature("st2").set("studystep", "sfs");
    model.sol("sol1").create("v2", "Variables");
    model.sol("sol1").feature("v2").set("initmethod", "sol");
    model.sol("sol1").feature("v2").set("initsol", "sol1");
    model.sol("sol1").feature("v2").set("notsolmethod", "sol");
    model.sol("sol1").feature("v2").set("notsol", "sol1");
    model.sol("sol1").feature("v2").set("control", "sfs");
    model.sol("sol1").create("s2", "Stationary");
    model.sol("sol1").feature("s2").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s2").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s2").feature("fc1").set("damp", 0.8);
    model.sol("sol1").feature("s2").feature("fc1").set("stabacc", "cflcmp");
    model.sol("sol1").feature("s2").feature("fc1").set("initcfl", 3);
    model.sol("sol1").feature("s2").feature("fc1").set("kppid", 0.65);
    model.sol("sol1").feature("s2").feature("fc1").set("kdpid", 0.05);
    model.sol("sol1").feature("s2").feature("fc1").set("kipid", 0.05);
    model.sol("sol1").feature("s2").feature("fc1").set("cfltol", 0.1);
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 300);
    model.sol("sol1").feature("s2").create("i1", "Iterative");
    model.sol("sol1").feature("s2").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("s2").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s2").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf ()");
    model.sol("sol1").feature("s2").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("gmglevels", 2);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("d1").label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf ()");
    model.sol("sol1").feature("s2").feature("fc1").set("linsolver", "i1");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s2").feature("fc1").set("damp", 0.8);
    model.sol("sol1").feature("s2").feature("fc1").set("stabacc", "cflcmp");
    model.sol("sol1").feature("s2").feature("fc1").set("initcfl", 3);
    model.sol("sol1").feature("s2").feature("fc1").set("kppid", 0.65);
    model.sol("sol1").feature("s2").feature("fc1").set("kdpid", 0.05);
    model.sol("sol1").feature("s2").feature("fc1").set("kipid", 0.05);
    model.sol("sol1").feature("s2").feature("fc1").set("cfltol", 0.1);
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 300);
    model.sol("sol1").feature("s2").feature().remove("fcDef");
    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("notsolvertype", "solnum");
    model.sol("sol1").feature("v2").set("notlistsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("notlistsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("control", "sfs");
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("solvertype", "solnum");
    model.sol("sol1").feature("v2").set("listsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("listsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("control", "sfs");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mcasegen", "manual");

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
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57);
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
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").set("data", "surf1");
    model.result("pg3").set("data", "surf1");
    model.result().setOnlyPlotWhenRequested(true);
    model.result().dataset("surf1").selection()
         .set(1, 4, 5, 6, 7, 8, 10, 11, 13, 14, 15, 16, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57);
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").selection().set(12);
    model.result("pg1").set("edges", false);
    model.result("pg1").feature().remove("slc1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "surf2");
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").set("expr", new String[]{"0", "spf.etaFS", "0"});
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("xnumber", 30);
    model.result("pg1").feature("arwv1").set("ynumber", 3);
    model.result("pg1").feature("arwv1").set("znumber", 30);
    model.result("pg1").feature("arwv1").set("scaleactive", true);
    model.result("pg1").feature("arwv1").set("scale", 0.07);
    model.result("pg1").feature("arwv1").create("col1", "Color");
    model.result("pg1").feature("arwv1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("data", "surf1");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("edges", false);
    model.result("pg4").create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("expr", "spf.muT");
    model.result("pg4").feature("slc1").set("quickxnumber", 1);
    model.result("pg4").feature().duplicate("slc2", "slc1");
    model.result("pg4").feature("slc2").set("quickplane", "zx");
    model.result("pg4").feature("slc2").set("quickynumber", 3);
    model.result("pg4").feature("slc2").set("inheritplot", "slc1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("data", "surf1");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("edges", false);
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("quickplane", "zx");
    model.result("pg5").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.05,1.8)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").feature("time").set("solnum", "last");
    model.study("std2").feature("time").setEntry("mesh", "geom1", "mesh1");

    model.sol().create("sol3");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(2);

    model.sol("sol3").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "last");

    model.sol("sol3").create("st1", "StudyStep");
    model.sol("sol3").feature("st1").set("study", "std2");
    model.sol("sol3").feature("st1").set("studystep", "time");
    model.sol("sol3").create("v1", "Variables");
    model.sol("sol3").feature("v1").feature("comp1_spatial_disp").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_spatial_disp").set("scaleval", "0.005529093329528276");
    model.sol("sol3").feature("v1").set("control", "time");
    model.sol("sol3").create("t1", "Time");
    model.sol("sol3").feature("t1").set("tlist", "range(0,0.05,1.8)");
    model.sol("sol3").feature("t1").set("plot", "off");
    model.sol("sol3").feature("t1").set("plotgroup", "pg1");
    model.sol("sol3").feature("t1").set("plotfreq", "tout");
    model.sol("sol3").feature("t1").set("probesel", "all");
    model.sol("sol3").feature("t1").set("probes", new String[]{});
    model.sol("sol3").feature("t1").set("probefreq", "tsteps");
    model.sol("sol3").feature("t1").set("rtol", 0.005);
    model.sol("sol3").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol3").feature("t1").set("atolglobalfactor", 0.05);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1")
         .set("atolmethod", new String[]{"comp1_ep", "unscaled", "comp1_k", "unscaled", "comp1_p", "scaled", "comp1_spatial_disp", "global", "comp1_spatial_lm", "global", 
         "comp1_spatial_lm_nv", "global", "comp1_spf_etaFS", "global", "comp1_u", "global", "comp1_ode1", "global"});
    model.sol("sol3").feature("t1")
         .set("atol", new String[]{"comp1_ep", "0.09*sqrt((0.01*1)^3)/(0.035*0.4500000000000006)", "comp1_k", "(0.01*1)^2", "comp1_p", "1e-3", "comp1_spatial_disp", "1e-3", "comp1_spatial_lm", "1e-3", 
         "comp1_spatial_lm_nv", "1e-3", "comp1_spf_etaFS", "1e-3", "comp1_u", "1e-3", "comp1_ode1", "1e-3"});
    model.sol("sol3").feature("t1")
         .set("atolvaluemethod", new String[]{"comp1_ep", "manual", "comp1_k", "manual", "comp1_p", "factor", "comp1_spatial_disp", "factor", "comp1_spatial_lm", "factor", 
         "comp1_spatial_lm_nv", "factor", "comp1_spf_etaFS", "factor", "comp1_u", "factor", "comp1_ode1", "factor"});
    model.sol("sol3").feature("t1")
         .set("atolfactor", new String[]{"comp1_ep", "0.1", "comp1_k", "0.1", "comp1_p", "1", "comp1_spatial_disp", "0.1", "comp1_spatial_lm", "0.1", 
         "comp1_spatial_lm_nv", "0.1", "comp1_spf_etaFS", "0.1", "comp1_u", "0.1", "comp1_ode1", "0.1"});
    model.sol("sol3").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol3").feature("t1").set("estrat", "exclude");
    model.sol("sol3").feature("t1").set("rhoinf", 0.5);
    model.sol("sol3").feature("t1").set("predictor", "constant");
    model.sol("sol3").feature("t1").set("maxorder", 2);
    model.sol("sol3").feature("t1").set("stabcntrl", true);
    model.sol("sol3").feature("t1").set("bwinitstepfrac", "0.01");
    model.sol("sol3").feature("t1").set("control", "time");
    model.sol("sol3").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol3").feature("t1").create("seDef", "Segregated");
    model.sol("sol3").feature("t1").create("se1", "Segregated");
    model.sol("sol3").feature("t1").feature("se1").feature().remove("ssDef");
    model.sol("sol3").feature("t1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol3").feature("t1").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_ode1"});
    model.sol("sol3").feature("t1").feature("se1").feature("ss1").set("subdamp", 1);
    model.sol("sol3").feature("t1").feature("se1").feature("ss1").set("subjtech", "onevery");
    model.sol("sol3").feature("t1").feature("se1").feature("ss1").set("subntolfact", 0.1);
    model.sol("sol3").feature("t1").feature("se1").feature("ss1").set("subtermconst", "iter");
    model.sol("sol3").feature("t1").feature("se1").feature("ss1").set("subiter", 1);
    model.sol("sol3").feature("t1").create("d1", "Direct");
    model.sol("sol3").feature("t1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol3").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol3").feature("t1").feature("d1").label("\u76f4\u63a5\uff0c\u89d2\u4f4d\u79fb");
    model.sol("sol3").feature("t1").feature("se1").feature("ss1").set("linsolver", "d1");
    model.sol("sol3").feature("t1").feature("se1").feature("ss1").label("\u89d2\u4f4d\u79fb");
    model.sol("sol3").feature("t1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol3").feature("t1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_u", "comp1_p"});
    model.sol("sol3").feature("t1").feature("se1").feature("ss2").set("subdamp", 0.8);
    model.sol("sol3").feature("t1").feature("se1").feature("ss2").set("subjtech", "once");
    model.sol("sol3").feature("t1").create("i1", "Iterative");
    model.sol("sol3").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol3").feature("t1").feature("i1").set("prefuntype", "left");
    model.sol("sol3").feature("t1").feature("i1").set("itrestart", 50);
    model.sol("sol3").feature("t1").feature("i1").set("rhob", 20);
    model.sol("sol3").feature("t1").feature("i1").set("maxlinit", 100);
    model.sol("sol3").feature("t1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol3").feature("t1").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol3").feature("t1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol3").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol3").feature("t1").feature("se1").feature("ss2").set("linsolver", "i1");
    model.sol("sol3").feature("t1").feature("se1").feature("ss2").label("\u901f\u5ea6 u\uff0c\u538b\u529b p");
    model.sol("sol3").feature("t1").feature("se1").create("ss3", "SegregatedStep");
    model.sol("sol3").feature("t1").feature("se1").feature("ss3").set("segvar", new String[]{"comp1_k", "comp1_ep"});
    model.sol("sol3").feature("t1").feature("se1").feature("ss3").set("subdamp", 0.8);
    model.sol("sol3").feature("t1").feature("se1").feature("ss3").set("subiter", 1);
    model.sol("sol3").feature("t1").feature("se1").feature("ss3").set("subtermconst", "iter");
    model.sol("sol3").feature("t1").feature("se1").feature("ss3").set("subjtech", "onevery");
    model.sol("sol3").feature("t1").feature("se1").feature("ss3").set("subntolfact", 0.1);
    model.sol("sol3").feature("t1").create("i2", "Iterative");
    model.sol("sol3").feature("t1").feature("i2").set("linsolver", "gmres");
    model.sol("sol3").feature("t1").feature("i2").set("prefuntype", "left");
    model.sol("sol3").feature("t1").feature("i2").set("itrestart", 50);
    model.sol("sol3").feature("t1").feature("i2").set("rhob", 20);
    model.sol("sol3").feature("t1").feature("i2").set("maxlinit", 200);
    model.sol("sol3").feature("t1").feature("i2").set("nlinnormuse", "on");
    model.sol("sol3").feature("t1").feature("i2").label("AMG\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol3").feature("t1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("iter", 0);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol3").feature("t1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol3").feature("t1").feature("se1").feature("ss3").set("linsolver", "i2");
    model.sol("sol3").feature("t1").feature("se1").feature("ss3").label("\u6e4d\u6d41\u53d8\u91cf");
    model.sol("sol3").feature("t1").feature("se1").create("ss4", "SegregatedStep");
    model.sol("sol3").feature("t1").feature("se1").feature("ss4")
         .set("segvar", new String[]{"comp1_spatial_disp", "comp1_spatial_lm", "comp1_spatial_lm_nv"});
    model.sol("sol3").feature("t1").feature("se1").feature("ss4").set("subdamp", 0.8);
    model.sol("sol3").feature("t1").feature("se1").feature("ss4").set("subjtech", "once");
    model.sol("sol3").feature("t1").feature("se1").feature("ss4").set("subiter", 1);
    model.sol("sol3").feature("t1").feature("se1").feature("ss4").set("subtermconst", "iter");
    model.sol("sol3").feature("t1").feature("se1").feature("ss4").set("subntolfact", 0.1);
    model.sol("sol3").feature("t1").create("d2", "Direct");
    model.sol("sol3").feature("t1").feature("d2").set("linsolver", "pardiso");
    model.sol("sol3").feature("t1").feature("d2").set("pivotperturb", 1.0E-13);
    model.sol("sol3").feature("t1").feature("d2").label("\u76f4\u63a5\uff0c\u7a7a\u95f4\u7f51\u683c\u4f4d\u79fb");
    model.sol("sol3").feature("t1").feature("se1").feature("ss4").set("linsolver", "d2");
    model.sol("sol3").feature("t1").feature("se1").feature("ss4").label("\u7a7a\u95f4\u7f51\u683c\u4f4d\u79fb");
    model.sol("sol3").feature("t1").feature("se1").set("ntolfact", 0.5);
    model.sol("sol3").feature("t1").feature("se1").set("segstabacc", "segaacc");
    model.sol("sol3").feature("t1").feature("se1").set("segaaccdim", 5);
    model.sol("sol3").feature("t1").feature("se1").set("segaaccmix", 0.9);
    model.sol("sol3").feature("t1").feature("se1").set("segaaccdelay", 1);
    model.sol("sol3").feature("t1").feature("se1").set("maxsegiter", 10);
    model.sol("sol3").feature("t1").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol3").feature("t1").feature("se1").feature("ll1").set("lowerlimit", "comp1.k 0 comp1.ep 0 ");
    model.sol("sol3").feature("t1").create("d3", "Direct");
    model.sol("sol3").feature("t1").feature("d3").set("linsolver", "pardiso");
    model.sol("sol3").feature("t1").feature("d3").set("pivotperturb", 1.0E-13);
    model.sol("sol3").feature("t1").feature("d3")
         .label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol3").feature("t1").create("d4", "Direct");
    model.sol("sol3").feature("t1").feature("d4").set("linsolver", "pardiso");
    model.sol("sol3").feature("t1").feature("d4").set("pivotperturb", 1.0E-13);
    model.sol("sol3").feature("t1").feature("d4").label("\u76f4\u63a5\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol3").feature("t1").feature().remove("fcDef");

    return model;
  }

  public static Model run3(Model model) {
    model.sol("sol3").feature("t1").feature().remove("seDef");
    model.sol("sol3").attach("std2");

    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u901f\u5ea6 (spf) 1");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").feature().create("slc1", "Slice");
    model.result("pg6").feature("slc1").label("\u5207\u9762");
    model.result("pg6").feature("slc1").set("showsolutionparams", "on");
    model.result("pg6").feature("slc1").set("smooth", "internal");
    model.result("pg6").feature("slc1").set("showsolutionparams", "on");
    model.result("pg6").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf3", "Surface");
    model.result().dataset("surf3").label("\u5916\u58c1 1");
    model.result().dataset("surf3").set("data", "dset3");
    model.result().dataset("surf3").selection().geom("geom1", 2);
    model.result().dataset("surf3").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57);
    model.result().dataset("surf3").selection().inherit(false);
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u538b\u529b (spf) 1");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("data", "surf3");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").feature("surf1").set("titletype", "none");
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature().create("con1", "Contour");
    model.result("pg7").feature("con1").label("\u538b\u529b");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("expr", "p");
    model.result("pg7").feature("con1").set("number", 40);
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("smooth", "internal");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("data", "parent");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u58c1\u5206\u8fa8\u7387 (spf) 1");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("data", "surf3");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");

    model.sol("sol3").runFromTo("st1", "v1");

    model.result("pg6").set("data", "dset3");
    model.result().dataset("surf3").set("data", "dset3");
    model.result("pg7").set("data", "surf3");
    model.result("pg8").set("data", "surf3");
    model.result().dataset("surf3").selection()
         .set(1, 4, 5, 6, 7, 8, 10, 11, 13, 14, 15, 16, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57);
    model.result().dataset().duplicate("surf4", "surf2");
    model.result().dataset("surf4").set("data", "dset3");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "dset3");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result("pg6").set("edges", false);
    model.result("pg6").feature().remove("slc1");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "surf4");
    model.result("pg6").feature("surf1").set("colortable", "WaveLight");
    model.result("pg6").create("arwv1", "ArrowVolume");
    model.result("pg6").feature("arwv1").set("xnumber", 30);
    model.result("pg6").feature("arwv1").set("ynumber", 3);
    model.result("pg6").feature("arwv1").set("znumber", 30);
    model.result("pg6").feature("arwv1").set("scaleactive", true);
    model.result("pg6").feature("arwv1").set("scale", 0.07);
    model.result("pg6").feature("arwv1").create("col1", "Color");
    model.result("pg6").feature("arwv1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("data", "surf3");
    model.result("pg6").feature("surf2").set("coloring", "uniform");
    model.result("pg6").feature("surf2").set("color", "gray");

    model.study("std2").feature("time").set("plot", true);
    model.study("std2").feature("time").set("plotgroup", "pg6");

    model.sol("sol3").feature("t1").set("minorder", 2);
    model.sol("sol3").feature("t1").set("bwinitstepfrac", 1);
    model.sol("sol3").runAll();

    model.result("pg6").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("edges", false);
    model.result("pg9").create("slc1", "Slice");
    model.result("pg9").feature("slc1").set("expr", "spf.muT");
    model.result("pg9").feature("slc1").set("quickxnumber", 1);
    model.result("pg9").feature().duplicate("slc2", "slc1");
    model.result("pg9").feature("slc2").set("quickplane", "zx");
    model.result("pg9").feature("slc2").set("quickynumber", 3);
    model.result("pg9").feature("slc2").set("inheritplot", "slc1");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("data", "surf3");
    model.result("pg9").feature("surf1").set("coloring", "uniform");
    model.result("pg9").feature("surf1").set("color", "gray");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "cpl1");
    model.result("pg10").set("edges", false);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").create("arws1", "ArrowSurface");
    model.result("pg10").feature("arws1").set("planecoordsys", "cartesian");
    model.result("pg10").feature("arws1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg10").feature("arws1")
         .set("descr", "\u901f\u5ea6\u573a \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg10").feature("arws1").set("xnumber", 20);
    model.result("pg10").feature("arws1").set("ynumber", 20);
    model.result("pg10").feature("arws1").set("scaleactive", true);
    model.result("pg10").feature("arws1").set("scale", 0.1);
    model.result("pg10").run();

    model.title("\u6405\u62cc\u5668\u4e2d\u7684\u81ea\u7531\u6db2\u9762");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5e26\u6321\u677f\u7684\u6e4d\u6d41\u6405\u62cc\u5668\u4e2d\u7684\u6e4d\u6d41\uff0c\u6f14\u793a\u5982\u4f55\u5728\u201c\u6405\u62cc\u5668\u6a21\u5757\u201d\u4e2d\u4f7f\u7528\u81ea\u7531\u6db2\u9762\u548c\u7a33\u6001\u81ea\u7531\u6db2\u9762\u7279\u5f81\u6765\u8bbe\u7f6e\u201c\u65cb\u8f6c\u673a\u68b0\uff0c\u6e4d\u6d41\u201d\u63a5\u53e3\u3002\u672c\u4f8b\u6267\u884c\u51bb\u7ed3\u8f6c\u5b50\u4eff\u771f\u548c\u77ac\u6001\u4eff\u771f\uff0c\u5e76\u5bf9\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\n\n\u6ce8\uff1a\u8be5\u6a21\u578b\u5305\u542b\u51bb\u7ed3\u8f6c\u5b50\u548c\u77ac\u6001\u7814\u7a76\uff0c\u6c42\u89e3\u65f6\u95f4\u7ea6\u4e3a\u4e00\u5929\uff1b\u5176\u4e2d\uff0c\u51bb\u7ed3\u8f6c\u5b50\u7814\u7a76\u7684\u6c42\u89e3\u65f6\u95f4\u53ea\u9700\u51e0\u4e2a\u5c0f\u65f6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("free_surface_mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
