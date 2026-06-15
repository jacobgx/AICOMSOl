/*
 * tubular_centrifuge.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:55 by COMSOL 6.3.0.290. */
public class tubular_centrifuge {

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
    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();

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
    model.study("std1").feature("wdi").setSolveFor("/physics/fpt", true);
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("outputmap", new String[]{});
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);
    model.study("std1").feature("frrot").setSolveFor("/physics/fpt", true);

    model.param().set("omega", "1000[rad/s]");
    model.param().descr("omega", "Angular speed");
    model.param().set("L_tube", "225[mm]");
    model.param().descr("L_tube", "Tube length");
    model.param().set("R_tube", "20[mm]");
    model.param().descr("R_tube", "Tube radius");
    model.param().set("R_feed", "5[mm]");
    model.param().descr("R_feed", "Feed radius");
    model.param().set("Vol_f", "2.5[l/min]");
    model.param().descr("Vol_f", "Fluid volumetric flow rate");
    model.param().set("dia_part", "20[um]");
    model.param().descr("dia_part", "Particle diameter");
    model.param().set("num_rel", "1000");
    model.param().descr("num_rel", "Number of particles per type");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R_tube");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L_tube");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "R_feed");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature().duplicate("wp2", "wp1");
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "L_tube");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(5);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(6);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(1, 2, 3, 4, 7, 8);

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

    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("angularVelocity", "omega");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("FullyDevelopedFlowOption", "V0");
    model.component("comp1").physics("spf").feature("inl1").set("V0fdf", "Vol_f");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel2");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("fpt").prop("StoreParticleStatusData")
         .setIndex("StoreParticleStatusData", 1, 0);
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop", "1200[kg/m^3]");
    model.component("comp1").physics("fpt").feature("pp1").set("dp", "dia_part");
    model.component("comp1").physics("fpt").create("pp2", "ParticlePropertiesOther", -1);
    model.component("comp1").physics("fpt").feature("pp2").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp2").set("rhop", "1500[kg/m^3]");
    model.component("comp1").physics("fpt").feature("pp2").set("dp", "dia_part");
    model.component("comp1").physics("fpt").feature().duplicate("pp3", "pp2");
    model.component("comp1").physics("fpt").feature("pp3").set("rhop", "2200[kg/m^3]");
    model.component("comp1").physics("fpt").create("rf1", "RotatingFrame", 3);
    model.component("comp1").physics("fpt").feature("rf1").set("normOmega", "omega");
    model.component("comp1").physics("fpt").create("df1", "DragForce", 3);
    model.component("comp1").physics("fpt").feature("df1").selection().set(1);
    model.component("comp1").physics("fpt").feature("df1").set("DragLaw", "SchillerNaumann");
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("df1").set("IncludeVirtualMassAndPressureGradientForces", true);
    model.component("comp1").physics("fpt").create("gf1", "GravityForce", 3);
    model.component("comp1").physics("fpt").feature("gf1").selection().set(1);
    model.component("comp1").physics("fpt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("fpt").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("fpt").feature("inl1").set("InitialPosition", "Density");
    model.component("comp1").physics("fpt").feature("inl1").setIndex("N", "num_rel", 0);
    model.component("comp1").physics("fpt").feature("inl1").set("dpro", "w");
    model.component("comp1").physics("fpt").feature("inl1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("inl1").set("SubtractMovingFrameVelocity", true);
    model.component("comp1").physics("fpt").feature().duplicate("inl2", "inl1");
    model.component("comp1").physics("fpt").feature("inl2").set("ReleasedParticleProperties", "pp2");
    model.component("comp1").physics("fpt").feature().duplicate("inl3", "inl2");
    model.component("comp1").physics("fpt").feature("inl3").set("ReleasedParticleProperties", "pp3");
    model.component("comp1").physics("fpt").create("out1", "Outlet", 2);
    model.component("comp1").physics("fpt").feature("out1").selection().named("sel2");
    model.component("comp1").physics("fpt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("fpt").feature("pcnt1").selection().named("sel3");
    model.component("comp1").physics("fpt").feature("pcnt1").set("ParticleSelection", "ParticleProperties");
    model.component("comp1").physics("fpt").feature("pcnt1").set("ReleasedParticleProperties", "pp1");
    model.component("comp1").physics("fpt").feature().duplicate("pcnt2", "pcnt1");
    model.component("comp1").physics("fpt").feature("pcnt2").set("ReleasedParticleProperties", "pp2");
    model.component("comp1").physics("fpt").feature().duplicate("pcnt3", "pcnt2");
    model.component("comp1").physics("fpt").feature("pcnt3").set("ReleasedParticleProperties", "pp3");

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.05,1.0)");
    model.study("std1").feature("time").setSolveFor("/frame/spatial1", false);
    model.study("std1").feature("time").setSolveFor("/physics/spf", false);
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").setSolveFor("/common/rot1", false);
    model.study("std1").feature("time").set("disabledcommon", new String[]{"rot1"});
    model.study("std1").feature("time").set("usesol", true);
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
    model.result().dataset("surf1").selection().set(1, 2, 3, 4, 7, 8);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 21, 0);
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
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg4").create("traj1", "ParticleTrajectories");
    model.result("pg4").feature("traj1").set("pointtype", "point");
    model.result("pg4").feature("traj1").set("linetype", "none");
    model.result("pg4").feature("traj1").create("col1", "Color");
    model.result("pg4").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 21, 0);
    model.result("pg5").label("\u5750\u6807\u7cfb\u901f\u5ea6 (fpt)");
    model.result("pg5").create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"fpt.rf1.vfx", "fpt.rf1.vfy", "fpt.rf1.vfz"});
    model.result("pg5").feature("arwv1").create("col1", "Color");
    model.result("pg5").feature("arwv1").feature("col1").set("expr", "fpt.rf1.Vf");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("expr", "w");
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickzmethod", "coord");
    model.result("pg1").feature("slc1").set("quickz", "range(0,(L_tube-0)/9,L_tube)");
    model.result("pg1").feature("slc1").set("colortable", "Prism");
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("number", 100);
    model.result("pg1").run();
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("expr", "w");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg4").feature("traj1").set("sphereradiusscaleactive", true);
    model.result("pg4").feature("traj1").set("sphereradiusscale", 1.5);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("traj1").feature("col1").set("expr", "fpt.rhop");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std2").feature("time").set("tlist", "range(1.0,0.05,2.0)");
    model.study("std2").feature("time").setSolveFor("/frame/spatial1", false);
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").setSolveFor("/common/rot1", false);
    model.study("std2").feature("time").set("disabledcommon", new String[]{"rot1"});
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").feature("time").set("solnum", "last");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("part2", "Particle");
    model.result().dataset("part2").set("solution", "sol4");
    model.result().dataset("part2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part2").set("geom", "geom1");
    model.result().dataset("part2").set("pgeom", "pgeom_fpt");
    model.result().dataset("part2").set("pgeomspec", "fromphysics");
    model.result().dataset("part2").set("physicsinterface", "fpt");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "part2");
    model.result("pg6").setIndex("looplevel", 21, 0);
    model.result("pg6").label("\u7c92\u5b50\u8f68\u8ff9 (fpt) 1");
    model.result("pg6").create("traj1", "ParticleTrajectories");
    model.result("pg6").feature("traj1").set("pointtype", "point");
    model.result("pg6").feature("traj1").set("linetype", "none");
    model.result("pg6").feature("traj1").create("col1", "Color");
    model.result("pg6").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").setIndex("looplevel", 21, 0);
    model.result("pg7").label("\u5750\u6807\u7cfb\u901f\u5ea6 (fpt) 1");
    model.result("pg7").create("arwv1", "ArrowVolume");
    model.result("pg7").feature("arwv1").set("expr", new String[]{"fpt.rf1.vfx", "fpt.rf1.vfy", "fpt.rf1.vfz"});
    model.result("pg7").feature("arwv1").create("col1", "Color");
    model.result("pg7").feature("arwv1").feature("col1").set("expr", "fpt.rf1.Vf");
    model.result("pg6").run();

    model.study().create("std3");
    model.study("std3").create("cmbsol", "CombineSolution");
    model.study("std3").feature("cmbsol").set("soloper", "remsol");
    model.study("std3").feature("cmbsol").set("removesol", "sol4");
    model.study("std3").feature("cmbsol").set("solnum", "manual");
    model.study("std3").create("cmbsol2", "CombineSolution");
    model.study("std3").feature("cmbsol2").set("cssol1", "sol1");
    model.study("std3").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().dataset().create("part3", "Particle");
    model.result().dataset("part3").set("solution", "sol5");
    model.result("pg4").run();
    model.result("pg4").set("data", "part3");
    model.result("pg4").stepLast(0);
    model.result("pg4").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg4");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "part3");
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").create("hist1", "Histogram");
    model.result("pg8").feature("hist1").set("markerpos", "datapoints");
    model.result("pg8").feature("hist1").set("linewidth", "preference");
    model.result("pg8").feature("hist1").set("expr", "if(fpt.sidx==1,qz,0)");
    model.result("pg8").feature("hist1").set("descractive", true);
    model.result("pg8").feature("hist1").set("method", "limits");
    model.result("pg8").feature("hist1").set("limits", "range(0.01,(L_tube-0.01)/9,L_tube)");
    model.result("pg8").feature("hist1").set("function", "discrete");
    model.result("pg8").feature("hist1").set("linewidth", 3);
    model.result("pg8").feature("hist1").set("legend", true);
    model.result("pg8").feature("hist1").set("autosolution", false);
    model.result("pg8").feature("hist1").set("autodescr", true);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("hist2", "hist1");
    model.result("pg8").run();
    model.result("pg8").feature("hist2").set("expr", "if(fpt.sidx==2,qz,0)");
    model.result("pg8").feature().duplicate("hist3", "hist2");
    model.result("pg8").run();
    model.result("pg8").feature("hist3").set("expr", "if(fpt.sidx==3,qz,0)");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("data", "part3");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "fpt.pcnt1.Nsel", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "1200[kg/m^3]", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "fpt.pcnt2.Nsel", 1);
    model.result("pg9").feature("glob1").setIndex("descr", "1500[kg/m^3]", 1);
    model.result("pg9").feature("glob1").setIndex("expr", "fpt.pcnt3.Nsel", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").feature("glob1").setIndex("descr", "2200[kg/m^3]", 2);
    model.result("pg9").feature("glob1").set("linewidth", 3);
    model.result("pg9").run();

    model.title("\u7ba1\u5f0f\u79bb\u5fc3\u673a");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e0d\u540c\u5bc6\u5ea6\u7684\u9897\u7c92\u5728\u7ba1\u5f0f\u79bb\u5fc3\u673a\u4e2d\u7684\u6c89\u964d\u8fc7\u7a0b\u3002\u7ba1\u5f0f\u79bb\u5fc3\u673a\u662f\u4e00\u79cd\u5e38\u7528\u7684\u79bb\u5fc3\u5206\u79bb\u8bbe\u5907\uff0c\u4e3b\u8981\u7528\u4e8e\u4ece\u6db2\u4f53\u4e2d\u5206\u79bb\u51fa\u6781\u7ec6\u5c0f\u7684\u56fa\u4f53\u9897\u7c92\u3002\u5f53\u9897\u7c92-\u6d41\u4f53\u6df7\u5408\u7269\u901a\u8fc7\u5165\u53e3\u8fdb\u5165\u65cb\u8f6c\u7ba1\u5185\u65f6\uff0c\u79bb\u5fc3\u529b\u4f1a\u52a0\u5feb\u9897\u7c92\u7684\u6c89\u964d\u901f\u5ea6\uff0c\u5bfc\u81f4\u9897\u7c92\u6c89\u79ef\u5728\u7ba1\u7684\u5185\u8868\u9762\u3002\u672c\u4f8b\u4f7f\u7528\u4e00\u7ef4\u76f4\u65b9\u56fe\u8bb0\u5f55\u6c89\u79ef\u5728\u7ba1\u58c1\u4e0a\u7684\u9897\u7c92\u5bc6\u5ea6\u7684\u5206\u5e03\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("tubular_centrifuge.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
