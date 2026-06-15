/*
 * continuous_mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:19 by COMSOL 6.3.0.290. */
public class continuous_mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Mixing_and_Separation");

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
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").insertFile("continuous_mixer_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("igf1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rpm", "15[1/min]", "Revolutions per minute");
    model.param().set("Omega", "(rpm*2*pi[rad])", "Angular velocity");
    model.param().set("nPart", "5e4", "Number of injected particles");
    model.param().set("A_in", "pi*r_in^2", "Inlet cross section area");
    model.param().set("Vol", "3.38[m^3]", "Tank volume");
    model.param().set("t_fill", "200/rpm", "Fill time");
    model.param().set("v_in", "Vol/A_in/t_fill", "Inlet velocity");
    model.param().set("deltaT", "10[s]", "Time step for residence time distribution");
    model.param().set("tEnd", "500[s]", "Simulation time end");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("h_dom_max", "0.1[m]", "Maximum element size, domain");
    model.param("par2").set("h_dom_min", "0.02[m]", "Minimum element size, domain");
    model.param("par2").set("h_dom_growth", "1.2", "Maximum growth rate, domain");
    model.param("par2").set("h_dom_curvef", "0.8", "Curvature factor, domain");
    model.param("par2").set("h_dom_narrowres", "1.5", "Resolution of narrow regions, domain");
    model.param("par2").set("h_bnd_max", "4[cm]", "Maximum element size, boundary");
    model.param("par2").set("h_bnd_min", "1.1[cm]", "Minimum element size, boundary");
    model.param("par2").set("h_bnd_curvef", "0.5", "Curvature factor, boundary");
    model.param("par2").set("h_inout_growth", "1.5", "Maximum growth rate, inlet and outlet boundary");
    model.param("par2").set("n_inlet", "10", "Number of element, inlet pipe");
    model.param("par2").set("n_inlet_hratio", "3", "Element ratio, inlet pipe");
    model.param("par2").set("h_blade_max", "0.7[cm]", "Maximum element size, impeller blade edge");
    model.param("par2").set("h_blade_min", "0.2[cm]", "Minimum element size, impeller blade edge");
    model.param("par2").set("h_blade_growth", "1.125", "Maximum growth rate, impeller blade");
    model.param("par2").set("bl_thicknessf", "2.3", "Thickness adjustment factor, boundary layer mesh");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(22, 23, 25, 26, 35, 44, 45, 46, 47, 48);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2")
         .set(31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 47, 48, 49, 50, 51, 52, 54, 60, 61, 62, 63, 64, 75, 76, 101, 102, 103, 104, 106, 108, 109, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139);

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

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("UseReducedPressure", true);
    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("sel1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("multipleInlets", false);
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "v_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(51, 52, 54, 55);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(10);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");

    model.component("comp1").common("rot1").selection().set(4);
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "constantAngularVelocity");
    model.component("comp1").common("rot1").set("angularVelocity", "Omega");
    model.component("comp1").common("rot1").set("rotationAxisBasePoint", new String[]{"0", "-0.175", "0.5"});
    model.component("comp1").common("rot1")
         .set("rotationAxis", new String[]{"0", "-sin(impellerTilt[deg])", "cos(impellerTilt[deg])"});

    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.study("std1").feature("frrot").setSolveFor("/physics/fpt", true);

    model.component("comp1").physics("fpt").prop("Formulation").setIndex("Formulation", "Massless", 0);
    model.component("comp1").physics("fpt").prop("StoreParticleReleaseStatistics")
         .setIndex("StoreParticleReleaseStatistics", 1, 0);
    model.component("comp1").physics("fpt").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("fpt").feature("pp1").set("v", new String[]{"u", "v", "w"});
    model.component("comp1").physics("fpt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("fpt").feature("inl1").selection().set(1);
    model.component("comp1").physics("fpt").feature("inl1").set("InitialPosition", "RandomPosition");
    model.component("comp1").physics("fpt").feature("inl1").setIndex("N", "nPart", 0);
    model.component("comp1").physics("fpt").create("out1", "Outlet", 2);
    model.component("comp1").physics("fpt").feature("out1").selection().set(51, 52, 54, 55);
    model.component("comp1").physics("fpt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("fpt").feature("pcnt1").selection().set(51, 52, 54, 55);

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "h_dom_max");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "h_dom_min");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", "h_dom_growth");
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", "h_dom_curvef");
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", "h_dom_narrowres");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 14, 15, 22, 23, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 53, 56);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "h_bnd_max");
    model.component("comp1").mesh("mesh1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", "h_bnd_min");
    model.component("comp1").mesh("mesh1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hcurve", "h_bnd_curvef");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "h_bnd_max/2.5");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 51, 52, 54, 55);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", "h_inout_growth");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection()
         .set(1, 2, 4, 6, 142, 144, 150, 155);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "h_bnd_max*0.45");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", "n_inlet");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", "n_inlet_hratio");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemcount", "n_inlet*2");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", "n_inlet_hratio");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", "h_blade_max");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmin", "h_blade_min");
    model.component("comp1").mesh("mesh1").create("cr1", "CornerRefinement");
    model.component("comp1").mesh("mesh1").feature("cr1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("cr1").selection().set(1, 2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("cr1").selection("boundary")
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 15, 22, 23, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 53, 56);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hgrad", "h_blade_growth");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1, 2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "trim");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", "bl_thicknessf");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 14, 15, 22, 23, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 53, 56);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4);

    model.sol("sol1").study("std1");

    model.study("std1").feature("frrot").set("notlistsolnum", 1);
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("listsolnum", 1);
    model.study("std1").feature("frrot").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "frrot");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "frrot");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_p", "comp1_u", "comp1_spf_inl1_Pinlfdf"});
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
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavarsactive", "on");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavars", new String[]{"comp1_spf_inl1_Pinlfdf"});
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
         .set("scgsmethod", "lines");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavarsactive", "on");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavars", new String[]{"comp1_spf_inl1_Pinlfdf"});
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

    return model;
  }

  public static Model run2(Model model) {
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
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("iter", 0);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
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
    model.sol("sol1").feature("s1").feature("d2").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d2").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d2").label("\u76f4\u63a5\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std2").feature("time").set("tlist", "range(0,1,100) range(100+deltaT,deltaT,tEnd)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/common/rot1", false);
    model.study("std2").feature("time").set("disabledcommon", new String[]{"rot1"});
    model.study("std2").feature("time").setSolveFor("/common/rot1", false);
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");

    model.sol().create("sol2");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3, 4);

    model.sol("sol2").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "auto");

    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");
    model.sol("sol2").feature("t1").set("tlist", "range(0,1,100) range(100+deltaT,deltaT,tEnd)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "Default");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 1.0E-5);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("reacf", true);
    model.sol("sol2").feature("t1").set("storeudot", true);
    model.sol("sol2").feature("t1").set("tstepsgenalpha", "strict");
    model.sol("sol2").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol2").feature("t1").set("timemethod", "genalpha");
    model.sol("sol2").feature("t1").set("estrat", "exclude");
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 0.1);
    model.sol("sol2").feature("t1").create("i1", "Iterative");
    model.sol("sol2").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("t1").feature("i1").create("ja1", "Jacobi");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "i1");
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 0.1);
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "part1");
    model.result("pg1").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg1").create("traj1", "ParticleTrajectories");
    model.result("pg1").feature("traj1").set("pointtype", "point");
    model.result("pg1").feature("traj1").set("linetype", "none");
    model.result("pg1").feature("traj1").create("col1", "Color");
    model.result("pg1").feature("traj1").feature("col1").set("expr", "fpt.V");

    model.sol("sol2").runAll();

    model.result("pg1").run();

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1")
         .set("igf1", 2, 3, 6, 10, 12, 13, 16, 17, 18, 19, 20, 21, 24, 36, 39, 41, 49, 53);

    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("genparaactive", true);
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("genparadist", "0.5 1.0 1.5 1.95");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 15, 22, 23, 25, 26, 27, 28, 29, 30, 32, 33, 34, 35, 36, 37, 38, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 53, 56);
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 15, 27, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 40, 42, 43, 49, 50, 53, 56);
    model.result().dataset().create("surf3", "Surface");
    model.result().dataset("surf3").selection().set(22, 23, 25, 26, 35, 44, 45, 46, 47, 48);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();

    model.component("comp1").view("view1").camera().set("zoomanglefull", 16.94299125671386);
    model.component("comp1").view("view1").camera().setIndex("position", -6.1942191123962, 0);
    model.component("comp1").view("view1").camera().setIndex("position", -14.47037696838378, 1);
    model.component("comp1").view("view1").camera().setIndex("position", 5.710288524627685, 2);
    model.component("comp1").view("view1").camera().setIndex("target", -0.0749998092651367, 0);
    model.component("comp1").view("view1").camera().setIndex("target", "0.0", 1);
    model.component("comp1").view("view1").camera().setIndex("target", 0.850014686584472, 2);
    model.component("comp1").view("view1").camera().setIndex("up", 0.1085651144385337, 0);
    model.component("comp1").view("view1").camera().setIndex("up", "0.2749564647674560", 1);
    model.component("comp1").view("view1").camera().setIndex("up", 0.955303847789764, 2);
    model.component("comp1").view("view1").camera().setIndex("rotationpoint", "-0.0749999880790710", 0);
    model.component("comp1").view("view1").camera().setIndex("rotationpoint", "0.0", 1);
    model.component("comp1").view("view1").camera().setIndex("rotationpoint", 0.850014686584472, 2);
    model.component("comp1").view("view1").camera().setIndex("viewoffset", -0.052503075450658, 0);
    model.component("comp1").view("view1").camera().setIndex("viewoffset", -0.001568899024277925, 1);

    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "cpl1");
    model.result("pg2").feature("surf2").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("tran1").set("transparency", 0.4);
    model.result("pg2").run();

    model.component("comp1").view("view1").set("ssao", true);

    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("data", "cpl1");
    model.result("pg2").feature("arws1").set("arrowcount", 900);
    model.result("pg2").feature("arws1").set("arrowtype", "cone");
    model.result("pg2").feature("arws1").set("color", "gray");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").run();
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("data", "surf2");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").run();
    model.result("pg3").create("con2", "Contour");
    model.result("pg3").feature("con2").set("data", "surf3");
    model.result("pg3").feature("con2").set("levelrounding", false);
    model.result("pg3").feature("con2").set("expr", "up(p)");
    model.result("pg3").feature("con2").set("number", 40);
    model.result("pg3").feature("con2").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("con2").feature("def1").set("expr", new String[]{"nx/sqrt(up(tremetric))", "", ""});
    model.result("pg3").feature("con2").feature("def1").setIndex("expr", "ny/sqrt(up(tremetric))", 1);
    model.result("pg3").feature("con2").feature("def1").setIndex("expr", "nz/sqrt(up(tremetric))", 2);
    model.result("pg3").feature("con2").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("con2").feature("def1").set("scale", 0.1);
    model.result("pg3").run();
    model.result("pg3").create("con3", "Contour");
    model.result("pg3").feature("con3").set("data", "surf3");
    model.result("pg3").feature("con3").set("expr", "down(p)");
    model.result("pg3").feature("con3").set("number", 40);
    model.result("pg3").feature("con3").set("levelrounding", false);
    model.result("pg3").feature("con3").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("con3").feature("def1")
         .set("expr", new String[]{"-nx/sqrt(down(tremetric))", "", ""});
    model.result("pg3").feature("con3").feature("def1").setIndex("expr", "-ny/sqrt(down(tremetric))", 1);
    model.result("pg3").feature("con3").feature("def1").setIndex("expr", "-nz/sqrt(down(tremetric))", 2);
    model.result("pg3").feature("con3").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("con3").feature("def1").set("scale", 0.1);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("data", "surf2");
    model.result("pg4").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").run();
    model.result("pg4").create("slit1", "SurfaceSlit");
    model.result("pg4").feature("slit1").set("data", "surf3");
    model.result("pg4").feature("slit1").set("upexpr", "spf.Delta_wPlus_u");
    model.result("pg4").feature("slit1").set("downexpr", "spf.Delta_wPlus_d");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevelinput", "interp", 0);
    model.result("pg5").setIndex("interp", "range(0,deltaT,tEnd)", 0);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "1-fpt.pcnt1.Nsel/nPart", 0);
    model.result("pg5").run();
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2")
         .setIndex("expr", "(fpt.pcnt1.Nsel-if(t>deltaT,at(t-deltaT,fpt.pcnt1.Nsel),0))/nPart", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("ymin", 0.5);
    model.result("pg5").set("ymax", 1.05);
    model.result("pg5").set("manualgrid", true);
    model.result("pg5").set("xspacing", 100);
    model.result("pg5").set("yspacing", 0.1);
    model.result("pg5").set("ysecspacing", 0.005);
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "Fraction in the tank", 0);
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("functype", "discrete");
    model.result("pg5").feature("glob2").set("legendmethod", "manual");
    model.result("pg5").feature("glob2").setIndex("legends", "Residence time distribution", 0);
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").feature("traj1").set("pointtype", "comettail");
    model.result("pg1").run();
    model.result("pg1").feature("traj1").feature("col1").set("rangecoloractive", true);
    model.result("pg1").feature("traj1").feature("col1").set("rangecolormax", 0.3);
    model.result("pg1").feature("traj1").feature("col1").set("rangecolormin", 0);
    model.result("pg1").run();

    model.title("\u8fde\u7eed\u6405\u62cc\u5668");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u4e00\u4e2a\u5e26\u6709\u975e\u5bf9\u79f0\u653e\u7f6e\u53f6\u8f6e\u7684\u91dc\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u6405\u62cc\u5668\u6a21\u5757\u201d\u96f6\u4ef6\u5e93\u4e2d\u7684\u96f6\u4ef6\u6765\u5b9a\u4e49\u91dc\u548c\u53f6\u8f6e\u7684\u51e0\u4f55\u5f62\u72b6\u3002\u6db2\u4f53\u4ece\u91dc\u7684\u9876\u90e8\u9644\u8fd1\u4e0d\u65ad\u5730\u88ab\u9001\u5165\u91dc\u4e2d\uff0c\u5e76\u901a\u8fc7\u5e95\u90e8\u7684\u51fa\u53e3\u6d41\u51fa\u3002\u672c\u4f8b\u4f7f\u7528\u51bb\u7ed3\u8f6c\u5b50\u5206\u6790\u6765\u6c42\u89e3\u7531\u53f6\u8f6e\u6405\u62cc\u4ea7\u751f\u7684\u6e4d\u6d41\u573a\u3002\u6a21\u578b\u4e2d\u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528\u7c92\u5b50\u8ffd\u8e2a\u5c06\u6405\u62cc\u8fc7\u7a0b\u53ef\u89c6\u5316\uff0c\u5e76\u8ba1\u7b97\u505c\u7559\u65f6\u95f4\u5206\u5e03\u3002\u5176\u4e2d\u5c06\u4e00\u4e2a\u5305\u542b 50,000 \u4e2a\u65e0\u8d28\u91cf\u9897\u7c92\u7684\u8109\u51b2\u6ce8\u5165\u6405\u62cc\u5668\uff0c\u5e76\u5728\u5176\u6d41\u7ecf\u6405\u62cc\u5668\u65f6\u8fdb\u884c\u8ddf\u8e2a\uff1b\u901a\u8fc7\u8ba1\u7b97\u5230\u8fbe\u51fa\u53e3\u7684\u9897\u7c92\u6570\u6765\u8ba1\u7b97\u505c\u7559\u65f6\u95f4\u5206\u5e03\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("continuous_mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
