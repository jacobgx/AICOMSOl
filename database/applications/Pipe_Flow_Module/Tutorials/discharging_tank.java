/*
 * discharging_tank.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:59 by COMSOL 6.3.0.290. */
public class discharging_tank {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("pfl", "FlowInPipes", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/pfl", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("H0", "25[m]", "\u6c34\u7bb1\u6c34\u4f4d");
    model.param().set("L1", "60[m]", "\u957f\u5ea6\uff0c\u7ba1 1");
    model.param().set("L2", "15[m]", "\u957f\u5ea6\uff0c\u7ba1 2");
    model.param().set("L3", "30.1[m]", "\u957f\u5ea6\uff0c\u7ba1 3");
    model.param().set("Dh", "15[cm]", "\u7ba1\u76f4\u5f84");
    model.param().set("V1", "90[m]", "\u9600\u4f4d\u7f6e");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L1", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L1", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L2", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "V1", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L2", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L1+L3", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L2", 4, 1);
    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("pfl").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("pfl").feature("pipe1").set("innerd", "Dh");
    model.component("comp1").physics("pfl").feature("pipe1").set("roughness", 7);
    model.component("comp1").physics("pfl").create("inl1", "Inlet", 0);
    model.component("comp1").physics("pfl").feature("inl1").set("spec", 5);
    model.component("comp1").physics("pfl").feature("inl1").set("pres", "101325[Pa]+H0*g_const*pfl.rho");
    model.component("comp1").physics("pfl").feature("inl1").selection().set(1);
    model.component("comp1").physics("pfl").feature("inl1").set("entrancetype", 2);
    model.component("comp1").physics("pfl").create("vf1", "VolumeForce", 1);
    model.component("comp1").physics("pfl").feature("vf1").selection().set(1, 2, 3);
    model.component("comp1").physics("pfl").create("bend1", "Bend", 0);
    model.component("comp1").physics("pfl").feature("bend1").selection().set(2, 3);
    model.component("comp1").physics("pfl").create("valve1", "Valve", 0);
    model.component("comp1").physics("pfl").feature("valve1").selection().set(4);
    model.component("comp1").physics("pfl").feature("valve1").set("valvetype", 3);
    model.component("comp1").physics("pfl").prop("PhysicsSymbols").set("PhysicsSymbols", true);
    model.component("comp1").physics("pfl").feature("fp1").set("showPhysicsSymbols", true);
    model.component("comp1").physics("pfl").feature("pipe1").set("showPhysicsSymbols", true);
    model.component("comp1").physics("pfl").feature("pr1").set("showPhysicsSymbols", true);
    model.component("comp1").physics("pfl").feature("lf1").set("showPhysicsSymbols", true);
    model.component("comp1").physics("pfl").feature("inl1").set("showPhysicsSymbols", true);
    model.component("comp1").physics("pfl").feature("vf1").set("showPhysicsSymbols", true);
    model.component("comp1").physics("pfl").feature("bend1").set("showPhysicsSymbols", true);
    model.component("comp1").physics("pfl").feature("valve1").set("showPhysicsSymbols", true);
    model.component("comp1").physics("pfl").prop("PhysicsSymbols").set("physicsSymbolButtons", "1");

    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u538b\u529b (pfl)");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "0.5*pfl.dh");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (pfl)");
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").set("arrowcount", 15);
    model.result("pg2").feature("arwl1").set("arrowlength", "normalized");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("col1", "Color");
    model.result("pg2").feature("arwl1").feature("col1").set("showcolordata", "off");
    model.result("pg2").feature("arwl1").feature("col1").set("expr", "pfl.U");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").feature("col1").set("expr", "pfl.Re");
    model.result("pg2").feature("arwl1").feature("col1").set("descr", "\u96f7\u8bfa\u6570");
    model.result("pg2").feature("arwl1").feature("col1").set("titletype", "auto");
    model.result("pg2").run();
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("text", "pfl.Qv");
    model.result("pg2").run();
    model.result("pg1").run();

    model.title("\u6c34\u7bb1\u6392\u6c34");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u8fde\u63a5\u6c34\u7bb1\u7684\u7ba1\u9053\u7cfb\u7edf\u4e2d\u7684\u538b\u964d\u548c\u521d\u59cb\u6d41\u7387\u3002\u201c\u7ba1\u9053\u6d41\u6a21\u5757\u201d\u4f7f\u7528\u5185\u7f6e\u7684\u6469\u64e6\u6a21\u578b\u6765\u8ba1\u7b97\u7ba1\u9053\u7684\u8868\u9762\u7c97\u7cd9\u5ea6\uff0c\u4ee5\u53ca\u5f2f\u7ba1\u548c\u9600\u4e2d\u7684\u538b\u529b\u635f\u5931\u3002");

    model.label("discharging_tank.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
