/*
 * buoyancy_water.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:33 by COMSOL 6.3.0.290. */
public class buoyancy_water {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Forced_and_Natural_Convection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("L", "10[cm]");
    model.param().descr("L", "\u6b63\u65b9\u5f62\u8fb9\u957f");
    model.param().set("DeltaT", "10[K]");
    model.param().descr("DeltaT", "\u6e29\u5dee");
    model.param().set("Tc", "283.15[K]");
    model.param().descr("Tc", "\u4f4e\u6e29");
    model.param().set("Th", "Tc+DeltaT");
    model.param().descr("Th", "\u9ad8\u6e29");
    model.param().set("rho", "1000[kg/m^3]");
    model.param().descr("rho", "\u5bc6\u5ea6");
    model.param().set("mu", "1.3e-3[N*s/m^2]");
    model.param().descr("mu", "\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("k", "0.58[W/(m*K)]");
    model.param().descr("k", "\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("Cp", "4.2[kJ/(kg*K)]");
    model.param().descr("Cp", "\u70ed\u5bb9");
    model.param().set("alpha", "9e-5[1/K]");
    model.param().descr("alpha", "\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.param().set("U0", "sqrt(g_const*alpha*DeltaT*L)");
    model.param().descr("U0", "\u6d6e\u529b\u5f15\u8d77\u7684\u5178\u578b\u901f\u5ea6");
    model.param().set("U1", "U0/sqrt(Pr)");
    model.param().descr("U1", "\u5178\u578b\u901f\u5ea6\u4f30\u8ba1\u503c");
    model.param().set("Pr", "mu*Cp/k");
    model.param().descr("Pr", "\u666e\u6717\u7279\u6570");
    model.param().set("Gr", "(U0*rho*L/mu)^2");
    model.param().descr("Gr", "\u683c\u62c9\u65af\u970d\u592b\u6570");
    model.param().set("Ra", "Pr*Gr");
    model.param().descr("Ra", "\u745e\u5229\u6570");
    model.param().set("Re0", "rho*U0*L/mu");
    model.param().descr("Re0", "\u4f7f\u7528 U0 \u5f97\u51fa\u7684\u96f7\u8bfa\u6570\u8fd1\u4f3c\u503c");
    model.param().set("Re1", "rho*U1*L/mu");
    model.param().descr("Re1", "\u4f7f\u7528 U1 \u5f97\u51fa\u7684\u96f7\u8bfa\u6570\u8fd1\u4f3c\u503c");
    model.param().set("eps_t", "L/(Ra)^0.25");
    model.param().descr("eps_t", "\u70ed\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("eps_m", "L/sqrt(Re1)");
    model.param().descr("eps_m", "\u52a8\u91cf\u8fb9\u754c\u5c42\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "L");
    model.component("comp1").geom("geom1").run("fin");

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

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(2);
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "(Tc+Th)/2");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "(Tc+Th)/2");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "Tc");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp2").selection().set(4);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "Th");

    model.component("comp1").multiphysics("nitf1").set("BoussinesqApproximation", true);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "DeltaT", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "1e-3 1e-2 1e-1 1 10", 0);

    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("PseudoTimeSetting", "Off");

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
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "nitf1.T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg4").feature().create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("solutionparams", "parent");
    model.result("pg4").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg4").feature("arws1").set("xnumber", 30);
    model.result("pg4").feature("arws1").set("ynumber", 30);
    model.result("pg4").feature("arws1").set("arrowtype", "cone");
    model.result("pg4").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("data", "parent");
    model.result("pg4").feature("arws1").feature().create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg4").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("arws1").set("xnumber", 20);
    model.result("pg4").feature("arws1").set("ynumber", 20);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "5[cm]", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "1[cm]", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "5[cm]", 1, 1);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8fb9\u754c\u5c42\u7684\u6e29\u5ea6");
    model.result("pg5").set("data", "cln1");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "T");
    model.result("pg5").feature("lngr1").set("descr", "\u6e29\u5ea6");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u8fb9\u754c\u5c42\u901f\u5ea6");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "spf.U");
    model.result("pg6").feature("lngr1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg6").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").physics().create("spf2", "LaminarFlow", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/spf2", false);

    model.component("comp2").physics("spf2").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp2").physics("spf2").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp2").physics().create("ht2", "HeatTransferInFluids", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ht2", false);

    model.component("comp2").physics("ht2").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp2").multiphysics().create("nitf2", "NonIsothermalFlow", 3);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf2", false);

    model.component("comp2").multiphysics("nitf2").set("Fluid_physics", "spf2");
    model.component("comp2").multiphysics("nitf2").set("Heat_physics", "ht2");

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/physics/spf2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf2", true);

    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"L", "L/2", "L"});
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp2").material("mat2").label("Water, liquid");
    model.component("comp2").material("mat2").set("family", "water");
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
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
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp2").material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp2").material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp2").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat2").propertyGroup("def").addInput("temperature");

    model.component("comp2").physics("spf2").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").physics("spf2").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp2").physics("spf2").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp2").physics("spf2").feature("prpc1").selection().set(4);
    model.component("comp2").physics("spf2").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("spf2").feature("sym1").selection().set(2);
    model.component("comp2").physics("ht2").prop("PhysicalModelProperty").set("Tref", "(Tc+Th)/2");
    model.component("comp2").physics("ht2").feature("init1").set("Tinit", "(Tc+Th)/2");
    model.component("comp2").physics("ht2").create("temp1", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht2").feature("temp1").selection().set(1);
    model.component("comp2").physics("ht2").feature("temp1").set("T0", "Tc");
    model.component("comp2").physics("ht2").create("temp2", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht2").feature("temp2").selection().set(6);
    model.component("comp2").physics("ht2").feature("temp2").set("T0", "Th");
    model.component("comp2").physics("ht2").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("ht2").feature("sym1").selection().set(2);
    model.component("comp2").physics("spf2").prop("AdvancedSettingProperty").set("PseudoTimeSetting", "Off");

    model.component("comp2").multiphysics("nitf2").set("BoussinesqApproximation", true);

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().set(2);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().set(1, 3, 5, 9);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("elemcount", 16);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("elemratio", 3);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp2").mesh("mesh2").run("map1");
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("elemcount", 8);
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("elemratio", 3);
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("reverse", true);
    model.component("comp2").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom(3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").selection().set(1, 3, 4, 5, 6);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blhmin", "1[mm]/5");
    model.component("comp2").mesh("mesh2").run();
    model.component("comp2").mesh().create("mesh3");
    model.component("comp2").mesh("mesh3").contribute("geom/detail", true);
    model.component("comp2").mesh("mesh3").create("rf1", "Reference");
    model.component("comp2").mesh("mesh3").feature("rf1").set("sequence", "mesh2");
    model.component("comp2").mesh("mesh3").create("ref1", "Refine");
    model.component("comp2").mesh("mesh3").feature("ref1").set("rmethod", "regular");
    model.component("comp2").mesh("mesh3").run();

    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "L", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "L", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "DeltaT", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "1e-3 1e-2 1e-1", 0);
    model.study("std2").feature().duplicate("stat1", "stat");
    model.study("std2").feature("stat1").setIndex("plistarr", "1 10", 0);
    model.study("std2").feature("stat1").setEntry("mesh", "geom2", "mesh3");
    model.study("std2").feature("stat1").set("useinitsol", true);
    model.study("std2").feature("stat1").set("initmethod", "sol");
    model.study("std2").feature("stat1").set("initstudy", "std2");
    model.study("std2").feature("stat1").set("solnum", "last");
    model.study("std2").create("cmbsol", "CombineSolution");
    model.study("std2").showAutoSequences("all");
    model.study("std2").feature("cmbsol").set("cssol1", "sol4");
    model.study("std2").feature("cmbsol").set("cssol2", "sol3");

    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("s2").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("s2").feature("fc1").set("damp", "0.8");
    model.sol("sol2").feature("s2").feature("i1").feature("mg1").set("prefun", "gmg");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset3").set("geom", "geom2");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u901f\u5ea6 (spf2)");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("slc1", "Slice");
    model.result("pg7").feature("slc1").label("\u5207\u9762");
    model.result("pg7").feature("slc1").set("showsolutionparams", "on");
    model.result("pg7").feature("slc1").set("smooth", "internal");
    model.result("pg7").feature("slc1").set("showsolutionparams", "on");
    model.result("pg7").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset3");
    model.result().dataset("surf1").selection().geom("geom2", 2);
    model.result().dataset("surf1").selection().set(1, 3, 4, 5, 6);
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u538b\u529b (spf2)");
    model.result("pg8").set("data", "surf1");
    model.result("pg8").setIndex("looplevel", 5, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u8868\u9762");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "p2");
    model.result("pg8").feature("surf1").set("colortable", "Dipole");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u6e29\u5ea6 (ht2)");
    model.result("pg9").feature().create("vol1", "Volume");
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("solutionparams", "parent");
    model.result("pg9").feature("vol1").set("expr", "T2");
    model.result("pg9").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg9").feature("vol1").set("smooth", "internal");
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("data", "parent");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf2)");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u58c1\u6e29");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "ht2.Tvar");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg10").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg10").feature("surf1").feature("sel1").selection().geom("geom2", 2);
    model.result("pg10").feature("surf1").feature("sel1").selection().set(1, 3, 4, 5, 6);
    model.result("pg10").feature().create("arwv1", "ArrowVolume");
    model.result("pg10").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg10").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg10").feature("arwv1").set("solutionparams", "parent");
    model.result("pg10").feature("arwv1").set("expr", new String[]{"nitf2.ux", "nitf2.uy", "nitf2.uz"});
    model.result("pg10").feature("arwv1").set("xnumber", 30);
    model.result("pg10").feature("arwv1").set("ynumber", 30);
    model.result("pg10").feature("arwv1").set("znumber", 30);
    model.result("pg10").feature("arwv1").set("arrowtype", "cone");
    model.result("pg10").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg10").feature("arwv1").set("data", "parent");
    model.result("pg10").feature("arwv1").feature().create("col1", "Color");
    model.result("pg10").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg10").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg10").feature("arwv1").feature("filt1").set("expr", "spf2.U>nitf2.Uave");
    model.result("pg7").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "zx");
    model.result("pg7").run();
    model.result("pg7").set("data", "mir1");
    model.result("pg7").run();
    model.result("pg9").run();
    model.result("pg9").set("data", "mir1");
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").set("data", "mir1");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").create("tran1", "Transparency");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("arwv1").set("xnumber", 15);
    model.result("pg10").feature("arwv1").set("ynumber", 15);
    model.result("pg10").feature("arwv1").set("znumber", 15);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").label("\u901f\u5ea6\uff0c\u524d\u5e73\u9762");
    model.result("pg11").set("data", "mir1");
    model.result("pg11").create("slc1", "Slice");
    model.result("pg11").feature("slc1").set("expr", "spf2.U");
    model.result("pg11").feature("slc1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg11").feature("slc1").set("quickplane", "zx");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("\u6e29\u5ea6\uff0c\u504f\u5dee 10 K");
    model.result("pg12").set("data", "mir1");
    model.result("pg12").create("slc1", "Slice");
    model.result("pg12").feature("slc1").set("expr", "T2");
    model.result("pg12").feature("slc1").set("descr", "\u6e29\u5ea6");
    model.result("pg12").feature("slc1").set("quickxnumber", 1);
    model.result("pg12").feature("slc1").set("colortable", "HeatCameraLight");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").create("arwv1", "ArrowVolume");
    model.result("pg12").feature("arwv1").set("expr", new String[]{"0", "v2", "w2"});
    model.result("pg12").feature("arwv1").set("xnumber", 1);
    model.result("pg12").feature("arwv1").set("ynumber", 20);
    model.result("pg12").feature("arwv1").set("znumber", 20);
    model.result("pg12").feature("arwv1").set("color", "black");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result().duplicate("pg13", "pg12");
    model.result("pg13").run();
    model.result("pg13").label("\u6e29\u5ea6\uff0c\u504f\u5dee 1 K");
    model.result("pg13").set("looplevel", new int[]{4});
    model.result("pg13").run();
    model.result().duplicate("pg14", "pg13");
    model.result("pg14").run();
    model.result("pg14").label("\u6e29\u5ea6\uff0c\u504f\u5dee 0.1 K");
    model.result("pg14").set("looplevel", new int[]{3});
    model.result("pg14").run();

    model.title("\u6c34\u4e2d\u7684\u6d6e\u529b\u6d41");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5145\u6ee1\u6c34\u7531\u4e24\u5757\u7ad6\u76f4\u5e73\u677f\u56f4\u6210\u8fb9\u754c\u7684\u8154\u4f53\u4e2d\u7684\u7a33\u6001\u81ea\u7136\u5bf9\u6d41\u3002\u4e3a\u4e86\u4ea7\u751f\u6d6e\u529b\u6d41\uff0c\u4e24\u5757\u677f\u52a0\u70ed\u5230\u4e0d\u540c\u6e29\u5ea6\uff0c\u5f97\u5230\u4ecb\u4e8e\u5c42\u6d41\u548c\u6e4d\u6d41\u4e4b\u95f4\u7684\u8fc7\u6e21\u6d41\u6548\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("buoyancy_water.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
