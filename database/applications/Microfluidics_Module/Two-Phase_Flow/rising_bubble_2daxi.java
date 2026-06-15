/*
 * rising_bubble_2daxi.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:12 by COMSOL 6.3.0.290. */
public class rising_bubble_2daxi {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Two-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ls", "LevelSet", "geom1");

    model.component("comp1").multiphysics().create("tpf1", "TwoPhaseFlowLevelSet", 2);
    model.component("comp1").multiphysics("tpf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("tpf1").set("Mathematics_physics", "ls");
    model.component("comp1").multiphysics("tpf1").selection().all();
    model.component("comp1").multiphysics().create("ww1", "WettedWall", 1);
    model.component("comp1").multiphysics("ww1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("ww1").set("Mathematics_physics", "ls");

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
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/ls", true);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/ww1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ww1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{5, 15});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 5");
    model.component("comp1").geom("geom1").feature("pol1").set("y", 10);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 2);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{0, 4});
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mpmat1", "Multiphase");
    model.component("comp1").material("mpmat1").selection().geom("geom1", 2);
    model.component("comp1").material("mpmat1").selection().set(1, 2, 3);
    model.component("comp1").material("mpmat1").selection().inherit(false);
    model.component("comp1").material("mpmat1").selection().embedded(false);
    model.component("comp1").material("mpmat1").set("vfDefinition", "ls");
    model.component("comp1").material("mpmat1").feature().create("phase2", "PhaseLink", "comp1");

    model.component("comp1").multiphysics("tpf1").set("multiphaseMaterialList", "mpmat1");
    model.component("comp1").multiphysics("tpf1").set("IncludeSurfaceTension", true);
    model.component("comp1").multiphysics("tpf1").set("SurfaceTensionCoefficient", "LibraryCoefficientLiquidLiquid");
    model.component("comp1").multiphysics("tpf1").set("LibraryCoefficientLiquidLiquid", "OlivoilWater20");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").label("Transformer oil");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"243.0", "273.0", "4492.20229-64.7408879*T^1+0.349900959*T^2-8.40477E-4*T^3+7.57041667E-7*T^4"}, {"273.0", "373.0", "91.4524999-1.33227058*T^1+0.00777680216*T^2-2.27271368E-5*T^3+3.32419673E-8*T^4-1.94631023E-11*T^5"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"223.0", "293.0", "-117056.38+1816.76208*T^1-10.305786*T^2+0.0256691919*T^3-2.36742424E-5*T^4"}, {"293.0", "373.0", "-13408.1491+123.044152*T^1-0.335401786*T^2+3.125E-4*T^3"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"223.0", "373.0", "1055.04607-0.581753034*T^1-6.40531689E-5*T^2"}});
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"223.0", "373.0", "0.134299084-8.04973822E-5*T^1"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mpmat1").feature("phase1").set("link", "mat1");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.material("mat2").label("Water");
    model.material("mat2").set("family", "water");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat2").propertyGroup("def").func("rho").label("Piecewise 3");
    model.material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat2").propertyGroup("def").func("k").label("Piecewise 4");
    model.material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat2").propertyGroup("def").func("cs").label("Interpolation");
    model.material("mat2").propertyGroup("def").func("cs")
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
    model.material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("an1").label("Analytic ");
    model.material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat2").propertyGroup("def").func("an2").label("Analytic 2");
    model.material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.material("mat2").propertyGroup("def").func("an2").set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat2").propertyGroup("def").func("an3").label("Analytic 3");
    model.material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("an3").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mpmat1").feature("phase2").set("link", "mat2");

    model.component("comp1").physics("ls").feature("lsm1").set("gamma", 0.2);
    model.component("comp1").physics("ls").feature("initfluid2").selection().set(1);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(8);
    model.component("comp1").physics("spf").feature("prpc1").set("CompensateForHydrostaticPressure", false);
    model.component("comp1").physics("spf").feature("init1").set("CompensateForHydrostaticPressure", false);

    model.component("comp1").multiphysics("ww1").selection().set(9, 10);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("rho_oil", "tpf1.rho1*tpf1.Vf1");
    model.component("comp1").variable("var1").descr("rho_oil", "\u5355\u4f4d\u4f53\u79ef\u7684\u6cb9\u8d28\u91cf");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.5/50,0.5)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("dataisaxisym", "off");
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
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "ls.Vf1");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "ls.Vf1");
    model.result("pg4").feature("con1").set("levelmethod", "levels");
    model.result("pg4").feature("con1").set("levels", "0.5");
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").feature("con1").set("color", "gray");
    model.result("pg4").feature("con1").set("smooth", "none");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().dataset("rev2").set("data", "dset1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls) 1");
    model.result("pg5").set("data", "rev2");
    model.result("pg5").setIndex("looplevel", 51, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("expr", "ls.Vf1");
    model.result("pg5").feature("iso1").set("levelmethod", "levels");
    model.result("pg5").feature("iso1").set("levels", "0.5");
    model.result("pg5").feature("iso1").set("coloring", "uniform");
    model.result("pg5").feature("iso1").set("color", "gray");
    model.result("pg5").feature("iso1").set("smooth", "none");
    model.result("pg5").feature("iso1").set("data", "parent");
    model.result("pg1").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").selection().all();
    model.result().numerical("int1").set("expr", new String[]{"rho_oil"});
    model.result().numerical("int1").set("descr", new String[]{"\u5355\u4f4d\u4f53\u79ef\u7684\u6cb9\u8d28\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"kg"});
    model.result().numerical("int1").setIndex("unit", "g", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("source", "table");
    model.result("pg6").feature("tblp1").set("table", "tbl1");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", 0);
    model.result("pg6").set("xmax", 0.5);
    model.result("pg6").set("ymin", 0.3637);
    model.result("pg6").set("ymax", 0.3896);
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortable", "WaveLight");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 9, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 13, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 17, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 27, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 33, 0);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("quickplane", "zx");
    model.result("pg5").feature("slc1").set("quickynumber", 1);
    model.result("pg5").feature("slc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg5").run();
    model.result("pg5").feature("iso1").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 12, 0);
    model.result("pg5").run();
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").selection().set(2, 8, 9, 10);
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("data", "edg1");
    model.result().dataset("rev3").set("revangle", 180);
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("data", "rev3");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").run();
    model.result("pg5").set("edges", false);

    model.view("view2").set("showaxisorientation", false);

    model.result("pg5").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg5");
    model.result().export("anim1").run();
    model.result().export("anim1").run();
    model.result("pg5").run();

    model.title("\u4e0a\u5347\u6c14\u6ce1");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u8f74\u5bf9\u79f0\u6a21\u578b\uff0c\u6a21\u62df\u4e00\u4e2a\u6cb9\u6ce1\u5728\u6c34\u4e2d\u7531\u4e8e\u6d6e\u529b\u800c\u4e0a\u5347\u3002\u672c\u4f8b\u4f7f\u7528\u6c34\u5e73\u96c6\u65b9\u6cd5\u8ffd\u8e2a\u6d41\u4f53\u7684\u754c\u9762\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("rising_bubble_2daxi.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
