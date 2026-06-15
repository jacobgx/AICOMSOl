/*
 * centrifugal_pump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:13 by COMSOL 6.3.0.290. */
public class centrifugal_pump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowkomega", "geom1");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
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
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("outputmap", new String[]{});
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").insertFile("centrifugal_pump_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("unisel1");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "extendedfaces");
    model.component("comp1").geom("geom1").feature("pard1").selection("extendedface").set("cmf2", 73, 13, 14, 94);
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("cmf2", 1);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").run("pard1");

    model.param().set("p_tot_in", "-0.05[bar]");
    model.param().descr("p_tot_in", "\u5165\u53e3\u603b\u538b");
    model.param().set("rot_rpm", "1000[rpm]");
    model.param().descr("rot_rpm", "\u8f6c\u901f");
    model.param().set("T_ref", "20[degC]");
    model.param().descr("T_ref", "\u53c2\u8003\u6e29\u5ea6");

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

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").set("probename", "m_in");
    model.component("comp1").probe("bnd1").set("type", "integral");
    model.component("comp1").probe("bnd1").selection().set(58);
    model.component("comp1").probe("bnd1").set("expr", "-rhoRef*(u*nx+v*ny+w*nz)");
    model.component("comp1").probe("bnd1").set("window", "window1");
    model.component("comp1").probe("bnd1").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().create("bnd2", "Boundary");
    model.component("comp1").probe("bnd2").set("intsurface", true);
    model.component("comp1").probe("bnd2").set("probename", "m_out");
    model.component("comp1").probe("bnd2").set("type", "integral");
    model.component("comp1").probe("bnd2").selection().set(9);
    model.component("comp1").probe("bnd2").set("expr", "rhoRef*(u*nx+v*ny+w*nz)");
    model.component("comp1").probe("bnd2").set("window", "window1");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "int_rot");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_csel1_bnd");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "int_in");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(57);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "int_out");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().set(6);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("rhoRef", "spf.rhoref");
    model.component("comp1").variable("var1").descr("rhoRef", "\u53c2\u8003\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("delta_p", "int_out(p)/int_out(1)-int_in(p)/int_in(1)");
    model.component("comp1").variable("var1").descr("delta_p", "\u9759\u538b\u589e\u52a0");
    model.component("comp1").variable("var1")
         .set("delta_p_tot", "((int_out(p+1/2*rhoRef*spf.U^2)/int_out(1)-int_in(p+1/2*rhoRef*spf.U^2)/int_in(1)))");
    model.component("comp1").variable("var1").descr("delta_p_tot", "\u603b\u538b\u589e\u52a0");
    model.component("comp1").variable("var1").set("Torque", "int_rot(+spf.T_tracx*y-spf.T_tracy*x)");
    model.component("comp1").variable("var1").descr("Torque", "\u626d\u77e9");
    model.component("comp1").variable("var1").set("Power", "abs(int_rot(rot1.alphat)*Torque/int_rot(1))");
    model.component("comp1").variable("var1").descr("Power", "\u8f74\u529f\u8017");
    model.component("comp1").variable("var1").set("flowrate", "int_in(u*nx+v*ny+w*nz)");
    model.component("comp1").variable("var1").descr("flowrate", "\u6d41\u7387");
    model.component("comp1").variable("var1").set("massflow", "rhoRef*flowrate");
    model.component("comp1").variable("var1").descr("massflow", "\u8d28\u91cf\u6d41");
    model.component("comp1").variable("var1").set("H_power", "abs(massflow*delta_p_tot/rhoRef)");
    model.component("comp1").variable("var1").descr("H_power", "\u7ed9\u5b9a\u7684\u6d41\u4f53\u529f\u7387");
    model.component("comp1").variable("var1").set("H", "delta_p_tot/(rhoRef*g_const)");
    model.component("comp1").variable("var1").descr("H", "\u538b\u5934");
    model.component("comp1").variable("var1").set("eta", "H_power/Power");
    model.component("comp1").variable("var1").descr("eta", "\u6cf5\u6548\u7387");

    model.component("comp1").common("rot1").selection().named("geom1_rev3_dom");
    model.component("comp1").common("rot1").set("revolutionsPerTime", "rot_rpm");
    model.component("comp1").common("rot1").set("rotationAxis", new String[]{"0", "0", "-1"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(58);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl1").set("PressureType", "TotalPressure");
    model.component("comp1").physics("spf").feature("inl1").set("AverageTotalPressure", true);
    model.component("comp1").physics("spf").feature("inl1").set("p0", "p_tot_in");
    model.component("comp1").physics("spf").feature("inl1").set("Uref", "3[m/s]");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").set("PressureType", "TotalPressure");
    model.component("comp1").physics("spf").feature("out1").selection().set(9);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(64, 65, 87, 93);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "ZeroFixedWall");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 5);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 3, 4, 5);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1, 3, 4, 5);
    model.component("comp1").mesh("mesh1").feature("bl1").set("trimminangle", 280);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhmin", "2.5e-4");
    model.component("comp1").mesh("mesh1").feature("bl1").feature().duplicate("blp2", "blp1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blhmin", "6e-5");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").selection()
         .set(24, 27, 31, 36, 42, 45, 48, 75, 105);
    model.component("comp1").mesh("mesh1").feature("bl1").feature().duplicate("blp3", "blp2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp3").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp3").set("blhmin", "1.2e-4");
    model.component("comp1").mesh("mesh1").feature("bl1").feature().duplicate("blp4", "blp3");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp4").selection()
         .set(15, 64, 65, 66, 67, 68, 69, 87, 88, 89, 90, 92, 93);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp4").set("blhmin", "2e-4");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature().move("map1", 1);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(11, 113);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(36, 247);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(16, 245);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(39, 54, 55, 83, 84, 97);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(66, 159);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(158, 189, 190);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("frrot").set("probesel", "none");
    model.study("std1").feature("frrot").set("useparam", true);
    model.study("std1").feature("frrot").setIndex("pname", "beta_in", 0);
    model.study("std1").feature("frrot").setIndex("plistarr", "", 0);
    model.study("std1").feature("frrot").setIndex("punit", "rad", 0);
    model.study("std1").feature("frrot").setIndex("pname", "beta_in", 0);
    model.study("std1").feature("frrot").setIndex("plistarr", "", 0);
    model.study("std1").feature("frrot").setIndex("punit", "rad", 0);
    model.study("std1").feature("frrot").setIndex("pname", "p_tot_in", 0);
    model.study("std1").feature("frrot").setIndex("plistarr", "range(-0.05,-0.1/4,-0.15)", 0);
    model.study("std1").feature("frrot").setIndex("punit", "bar", 0);
    model.study("std1").feature("frrot").set("pcontinuationmode", "no");
    model.study("std1").feature("frrot").set("preusesol", "yes");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s2").feature("se1").set("probesel", "all");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");
    model.component("comp1").probe("bnd2").genResult("none");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("\u5207\u9762");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 15, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 59, 60, 61, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 82, 83, 84, 85, 87, 88, 89, 90, 92, 93, 94, 97, 98, 99, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").setIndex("looplevel", 5, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg4").set("data", "surf1");
    model.result("pg4").setIndex("looplevel", 5, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().set(1, 3, 4, 5, 6);
    model.result().dataset("surf1").selection().named("geom1_unisel1");
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").selection().set(58);
    model.result().numerical("av1").setIndex("expr", "w", 0);
    model.result().numerical("av1").setIndex("unit", "m/s", 0);
    model.result().numerical("av1").setIndex("descr", "\u901f\u5ea6\u573a\uff0cz \u5206\u91cf", 0);
    model.result().numerical().create("av2", "AvSurface");
    model.result().numerical("av2").set("intvolume", true);
    model.result().numerical("av2").selection().set(58);
    model.result().numerical("av2").setIndex("expr", "p", 0);
    model.result().numerical("av2").setIndex("unit", "bar", 0);
    model.result().numerical("av2").setIndex("descr", "\u538b\u529b", 0);
    model.result().numerical().create("av3", "AvVolume");
    model.result().numerical("av3").selection().all();
    model.result().numerical("av3").label("\u6027\u80fd\u6570\u636e");
    model.result().numerical("av3").setIndex("expr", "delta_p", 0);
    model.result().numerical("av3").setIndex("unit", "N/m^2", 0);
    model.result().numerical("av3").setIndex("descr", "\u9759\u538b\u589e\u52a0", 0);
    model.result().numerical("av3").setIndex("expr", "delta_p_tot", 1);
    model.result().numerical("av3").setIndex("unit", "N/m^2", 1);
    model.result().numerical("av3").setIndex("descr", "\u603b\u538b\u589e\u52a0", 1);
    model.result().numerical("av3").setIndex("expr", "Torque", 2);
    model.result().numerical("av3").setIndex("unit", "N*m", 2);
    model.result().numerical("av3").setIndex("descr", "\u626d\u77e9", 2);
    model.result().numerical("av3").setIndex("expr", "Power", 3);
    model.result().numerical("av3").setIndex("unit", "N*m/s", 3);
    model.result().numerical("av3").setIndex("descr", "\u8f74\u529f\u8017", 3);
    model.result().numerical("av3").setIndex("expr", "H_power", 4);
    model.result().numerical("av3").setIndex("unit", "N*m/s", 4);
    model.result().numerical("av3").setIndex("descr", "\u7ed9\u5b9a\u7684\u6d41\u4f53\u529f\u7387", 4);
    model.result().numerical("av3").setIndex("expr", "eta", 5);
    model.result().numerical("av3").setIndex("unit", 1, 5);
    model.result().numerical("av3").setIndex("descr", "\u6cf5\u6548\u7387", 5);
    model.result().numerical("av3").setIndex("expr", "H", 6);
    model.result().numerical("av3").setIndex("unit", 1, 6);
    model.result().numerical("av3").setIndex("descr", "\u538b\u5934", 6);
    model.result().numerical("av3").setIndex("expr", "flowrate", 7);
    model.result().numerical("av3").setIndex("unit", "l/min", 7);
    model.result().numerical("av3").setIndex("descr", "\u6d41\u7387", 7);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u6027\u80fd\u6570\u636e");
    model.result().numerical("av3").set("table", "tbl2");
    model.result().numerical("av3").setResult();
    model.result().table("tbl2").label("\u6027\u80fd\u6570\u636e");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6cf5\u7279\u6027\u66f2\u7ebf");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("table", "tbl2");
    model.result("pg5").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg5").feature("tblp1").set("plotcolumns", new int[]{8});
    model.result("pg5").feature("tblp1").set("xaxisdata", 9);
    model.result("pg5").feature("tblp1").set("linemarker", "point");
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").run();
    model.result("pg2").feature("slc1").set("titletype", "none");
    model.result("pg2").feature("slc1").set("quickplane", "xy");
    model.result("pg2").feature("slc1").set("quickznumber", 1);
    model.result("pg2").feature("slc1").set("interactive", true);
    model.result("pg2").feature("slc1").set("shift", -0.04);
    model.result("pg2").feature("slc1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("data", "surf1");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("slc2", "Slice");
    model.result("pg2").feature("slc2").set("expr", "p");
    model.result("pg2").feature("slc2").set("unit", "bar");
    model.result("pg2").feature("slc2").set("titletype", "manual");
    model.result("pg2").feature("slc2")
         .set("title", "\u76f8\u5bf9\u538b\u529b\uff08\u5de6\uff0cPa\uff09- \u901f\u5ea6\uff08\u53f3\uff0cm/s\uff09");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("slc2").set("quickplane", "xy");
    model.result("pg2").feature("slc2").set("quickznumber", 1);
    model.result("pg2").feature("slc2").set("interactive", true);
    model.result("pg2").feature("slc2").set("colortable", "AuroraAustralis");
    model.result("pg2").feature("slc2").set("shift", -0.04);
    model.result("pg2").feature("slc2").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("slc2").feature("def1").set("expr", new String[]{"8[cm]*sqrt(2)", "", ""});
    model.result("pg2").feature("slc2").feature("def1").setIndex("expr", "-8[cm]*sqrt(2)", 1);
    model.result("pg2").feature("slc2").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("slc2").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("slc3", "Slice");
    model.result("pg2").feature("slc3").set("titletype", "none");
    model.result("pg2").feature("slc3").set("quickplane", "zx");
    model.result("pg2").feature("slc3").set("quickynumber", 1);
    model.result("pg2").feature("slc3").set("interactive", true);
    model.result("pg2").feature("slc3").set("inheritplot", "slc1");
    model.result("pg2").feature("slc3").set("shift", 0.006);
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "surf1");
    model.result("pg2").feature("surf2").set("expr", "1");
    model.result("pg2").feature("surf2").set("titletype", "manual");
    model.result("pg2").feature("surf2").set("coloring", "uniform");
    model.result("pg2").feature("surf2").set("color", "gray");
    model.result("pg2").feature("surf2").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("def1").set("expr", new String[]{"8[cm]*sqrt(2)", "", ""});
    model.result("pg2").feature("surf2").feature("def1").setIndex("expr", "-8[cm]*sqrt(2)", 1);
    model.result("pg2").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "auto");
    model.result("pg2").set("legendpos", "alternating");
    model.result("pg2").run();
    model.result("pg2").feature("slc1").set("colorlegend", true);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("unit", "bar");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("data", "surf1");
    model.result("pg4").feature("surf1").set("expr", "spf.d_w_plus");
    model.result("pg4").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u8d28\u91cf\u6d41 (kg/s)");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("linemarker", "cycle");
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "m_in (kg/s)", 0);
    model.result("pg1").feature("tblp1").setIndex("legends", "m_out  (kg/s)", 1);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5916\u58c1 2");
    model.result().dataset("surf2").selection()
         .set(1, 2, 3, 5, 7, 8, 10, 11, 15, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 56, 59, 60, 61, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 82, 85, 88, 89, 90, 92, 94, 99, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115);
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "dset4");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", 0.0125);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset4");
    model.result("pg6").setIndex("looplevel", 2, 0);
    model.result("pg6").set("edges", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "surf2");
    model.result("pg6").feature("surf1").setIndex("looplevel", 2, 0);
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").run();
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("data", "cpl1");
    model.result("pg6").feature("surf2").setIndex("looplevel", 2, 0);
    model.result("pg6").feature("surf2").set("colortable", "JupiterAuroraBorealis");
    model.result("pg6").run();
    model.result("pg6").create("str1", "StreamlineSurface");
    model.result("pg6").feature("str1").set("data", "cpl1");
    model.result("pg6").feature("str1").setIndex("looplevel", 2, 0);
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("udist", 0.01);
    model.result("pg6").feature("str1").set("linetype", "tube");
    model.result("pg6").feature("str1").set("radiusexpr", "0.05");
    model.result("pg6").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg6").feature("str1").set("tuberadiusscale", 0.005);
    model.result("pg6").feature("str1").set("color", "custom");
    model.result("pg6").feature("str1")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg6").run();
    model.result("pg6").create("str2", "Streamline");
    model.result("pg6").feature("str2").set("selnumber", 14);
    model.result("pg6").feature("str2").selection().set(58);
    model.result("pg6").feature("str2").set("linetype", "tube");
    model.result("pg6").feature("str2").set("radiusexpr", "0.05");
    model.result("pg6").feature("str2").set("tuberadiusscaleactive", true);
    model.result("pg6").feature("str2").set("tuberadiusscale", 0.005);
    model.result("pg6").feature("str2").set("color", "custom");
    model.result("pg6").feature("str2")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg6").run();

    model.title("\u79bb\u5fc3\u6cf5");

    model
         .description("\u79bb\u5fc3\u6cf5\u7684\u5e94\u7528\u904d\u53ca\u5404\u884c\u5404\u4e1a\u3002\u6a21\u578b\u4e2d\u7684\u6cf5\u5c3a\u5bf8\u6839\u636e\u6c7d\u8f66\u5e94\u7528\u4e2d\u7684\u5178\u578b\u5c3a\u5bf8\u8fdb\u884c\u8bbe\u7f6e\uff0c\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u51bb\u7ed3\u8f6c\u5b50\u8fd1\u4f3c\u6765\u5efa\u7acb\u65cb\u8f6c\u673a\u68b0\u4eff\u771f\uff0c\u6a21\u62df\u79bb\u5fc3\u6cf5\u3002\u5176\u4e2d\u4f7f\u7528\u7ec4\u4ef6\u8026\u5408\u8ba1\u7b97\u6cf5\u7684\u4e00\u7cfb\u5217\u4e3b\u8981\u53c2\u6570\uff0c\u5e76\u4e13\u95e8\u8ba1\u7b97\u4e86\u6cf5\u7279\u6027\u66f2\u7ebf\u3002\u901a\u8fc7 COMSOL \u51e0\u4f55\u5de5\u5177\u6784\u5efa\u4e86\u6a21\u578b\u7684\u51e0\u4f55\u7ed3\u6784\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("centrifugal_pump.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
