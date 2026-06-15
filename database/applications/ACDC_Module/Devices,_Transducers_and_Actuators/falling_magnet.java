/*
 * falling_magnet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class falling_magnet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Transducers_and_Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ge", true);

    model.param().set("mr", "5[mm]");
    model.param().descr("mr", "\u78c1\u94c1\u534a\u5f84");
    model.param().set("mh", "10[mm]");
    model.param().descr("mh", "\u78c1\u94c1\u9ad8\u5ea6");
    model.param().set("r_i", "6[mm]");
    model.param().descr("r_i", "\u94dc\u7ba1\u5185\u534a\u5f84");
    model.param().set("r_o", "8[mm]");
    model.param().descr("r_o", "\u94dc\u7ba1\u5916\u534a\u5f84");
    model.param().set("dm", "7.4[g/(cm)^3]");
    model.param().descr("dm", "\u78c1\u94c1\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"mr", "mh"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-mh/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r1", 2, 3);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 1);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"r_o-r_i", "100"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"r_i", "-50"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"r_o-r_i", "40"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"r_i", "-20"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new int[]{30, 100});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new int[]{0, -50});
    model.component("comp1").geom("geom1").feature("r4").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r4").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r4").setIndex("layer", 10, 0);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection("sel1").label("\u78c1\u94c1");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(3, 4, 5);
    model.component("comp1").selection("sel2").label("\u94dc\u7ba1");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intmag");
    model.component("comp1").cpl("intop1").selection().named("sel1");
    model.component("comp1").cpl("intop1").set("axisym", false);
    model.component("comp1").cpl("intop1").label("\u5bf9\u78c1\u94c1\u6c42\u79ef\u5206");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("m", "intmag(2*pi*r*dm)");
    model.component("comp1").variable("var1").descr("m", "\u78c1\u94c1\u8d28\u91cf");
    model.component("comp1").variable("var1").set("Fg", "m*g_const");
    model.component("comp1").variable("var1").descr("Fg", "\u78c1\u94c1\u91cd\u529b");

    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "inttube");
    model.component("comp1").cpl("intop2").selection().named("sel2");
    model.component("comp1").cpl("intop2").set("axisym", false);
    model.component("comp1").cpl("intop2").label("\u5728\u94dc\u7ba1\u4e0a\u6c42\u79ef\u5206");

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("Fz", "inttube(-mf.FLtzz*2*pi*r)");
    model.component("comp1").variable("var2").descr("Fz", "z \u65b9\u5411\u7684\u6d1b\u4f26\u5179\u529b");
    model.component("comp1").variable("var2").set("a", "(Fg-Fz)/m");
    model.component("comp1").variable("var2").descr("a", "\u78c1\u94c1\u52a0\u901f\u5ea6");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().set(7);
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "v", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "d(v,t)-(Fg-Fz)/m", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u78c1\u94c1\u901f\u5ea6", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "velocity");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "acceleration");
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").label("\u5b89\u57f9\u5b9a\u5f8b - \u78c1\u94c1");
    model.component("comp1").physics("mf").feature("als1").selection().named("sel1");
    model.component("comp1").physics("mf").feature("als1").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als1").set("e_crel_BH_RemanentFluxDensity", new int[]{0, 0, 1});
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als2").label("\u5b89\u57f9\u5b9a\u5f8b - \u94dc\u7ba1");
    model.component("comp1").physics("mf").feature("als2").selection().named("sel2");
    model.component("comp1").physics("mf").create("vlt1", "Velocity", 2);
    model.component("comp1").physics("mf").feature("vlt1").selection().named("sel2");
    model.component("comp1").physics("mf").feature("vlt1").set("v", new String[]{"0", "0", "v"});
    model.component("comp1").physics("mf").create("pmc1", "PerfectMagneticConductor", 1);
    model.component("comp1").physics("mf").feature("pmc1").selection().set(2, 7, 10, 15, 17, 20, 22, 23, 24);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat2").label("N50 (Sintered NdFeB)");
    model.component("comp1").material("mat2").set("family", "chrome");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").set("normBr", "1.41[T]");
    model.component("comp1").material("mat1").selection().named("sel2");
    model.component("comp1").material("mat2").selection().named("sel1");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.001,0.05)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.001);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "mf.normJ");
    model.result("pg2").feature("surf1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"Fz"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"z \u65b9\u5411\u7684\u6d1b\u4f26\u5179\u529b"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg3").run();
    model.result("pg3").feature("glob1").label("\u6d1b\u4f26\u5179\u529b\uff0cFz");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").label("\u7ec8\u6781\u901f\u5ea6");
    model.result("pg4").feature("glob1").setIndex("unit", "cm/s", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"a"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u78c1\u94c1\u52a0\u901f\u5ea6"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"m/s^2"});
    model.result("pg5").feature("glob1").label("\u78c1\u94c1\u52a0\u901f\u5ea6");
    model.result("pg5").run();

    model.title("\u78c1\u94c1\u5728\u94dc\u7ba1\u4e2d\u4e0b\u843d");

    model
         .description("\u4e00\u4e2a\u67f1\u72b6\u78c1\u94c1\u5728\u94dc\u7ba1\u4e2d\u4e0b\u843d\uff0c\u4f1a\u5728\u7ba1\u58c1\u4e0a\u4ea7\u751f\u611f\u5e94\u6da1\u6d41\uff0c\u6da1\u6d41\u53cd\u8fc7\u6765\u4ea7\u751f\u4e00\u4e2a\u4e0e\u78c1\u94c1\u78c1\u573a\u76f8\u53cd\u7684\u78c1\u573a\uff0c\u5e76\u4ea7\u751f\u5236\u52a8\u529b\uff0c\u51cf\u7f13\u78c1\u94c1\u7684\u4e0b\u843d\u901f\u5ea6\u3002\u968f\u7740\u901f\u5ea6\u7684\u52a0\u5feb\uff0c\u8fd9\u4e2a\u53cd\u4f5c\u7528\u529b\u4f1a\u589e\u5927\u3002\u56e0\u6b64\uff0c\u5b58\u5728\u4e00\u4e2a\u81ea\u7531\u6c89\u964d\u901f\u5ea6\uff0c\u5728\u8fd9\u4e2a\u901f\u5ea6\u4e0b\uff0c\u78c1\u5236\u52a8\u529b\u7b49\u4e8e\u91cd\u529b\u3002\u672c\u4f8b\u8ba1\u7b97\u78c1\u94c1\u4e0b\u843d\u540e\u8fbe\u5230\u81ea\u7531\u6c89\u964d\u901f\u5ea6\u65f6\u7684\u901f\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("falling_magnet.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
