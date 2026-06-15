/*
 * geothermal_heating.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:57 by COMSOL 6.3.0.290. */
public class geothermal_heating {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("nipfl", "NonisothermalPipeFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/nipfl", true);

    model.component("comp1").geom("geom1").insertFile("geothermal_heating_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7ed9\u6c34\u548c\u56de\u6c34\u7ba1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", 4);
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u805a\u4e59\u70ef\u7ba1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("contributeto", "csel2");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 284, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 2, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 288, 1, 1);
    model.component("comp1").func("int1").setIndex("table", 4, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 291, 2, 1);
    model.component("comp1").func("int1").setIndex("table", 6, 3, 0);
    model.component("comp1").func("int1").setIndex("table", 293, 3, 1);
    model.component("comp1").func("int1").setIndex("argunit", "m", 0);
    model.component("comp1").func("int1").setIndex("fununit", "K", 0);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("T_pond", "int1(z)", "\u6c34\u6e29");
    model.component("comp1").variable("var1").set("d_wall", "2[mm]", "\u7ba1\u58c1\u539a\u5ea6");
    model.component("comp1").variable("var1")
         .set("k_wall", "0.46[W/m/K]", "\u9ad8\u5bc6\u5ea6\u805a\u4e59\u70ef\u7684\u5bfc\u70ed\u7cfb\u6570");

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

    model.component("comp1").physics("nipfl").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("nipfl").feature("pipe1").set("innerd", "20[mm]");
    model.component("comp1").physics("nipfl").feature("temp1").set("Tin", "5[degC]");
    model.component("comp1").physics("nipfl").create("pipe2", "PipeProperties", 1);
    model.component("comp1").physics("nipfl").feature("pipe2").selection().named("geom1_csel1_edg");
    model.component("comp1").physics("nipfl").feature("pipe2").set("shape", "Round");
    model.component("comp1").physics("nipfl").feature("pipe2").set("innerd", "50[mm]");
    model.component("comp1").physics("nipfl").create("wht1", "WallHeatTransfer", 1);
    model.component("comp1").physics("nipfl").feature("wht1").selection().named("geom1_csel2_edg");
    model.component("comp1").physics("nipfl").feature("wht1").set("Text", "T_pond");
    model.component("comp1").physics("nipfl").feature("wht1").create("intfilm1", "InternalFilmResistance", 1);
    model.component("comp1").physics("nipfl").feature("wht1").create("wall1", "WallLayer", 1);
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall1").set("kChoice", "UserDefined");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall1").set("k", "k_wall");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall1").set("deltawChoice", "UserDefined");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall1").set("item.deltaw", "d_wall");
    model.component("comp1").physics("nipfl").feature("wht1").create("extfilm1", "ExternalFilmResistance", 1);
    model.component("comp1").physics("nipfl").feature("wht1").feature("extfilm1").set("externalMaterial", "mat1");
    model.component("comp1").physics("nipfl").feature("wht1").feature("extfilm1").set("u", "0.2[m/s]");
    model.component("comp1").physics("nipfl").create("inl1", "Inlet", 0);
    model.component("comp1").physics("nipfl").feature("inl1").selection().set(1);
    model.component("comp1").physics("nipfl").feature("inl1").set("spec", 1);
    model.component("comp1").physics("nipfl").feature("inl1").set("qv0", "4[l/s]");
    model.component("comp1").physics("nipfl").create("hofl1", "HeatOutflow", 0);
    model.component("comp1").physics("nipfl").feature("hofl1").selection().set(2);

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u538b\u529b (nipfl)");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("expr", "p");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "0.5*nipfl.dh");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (nipfl)");
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").set("showsolutionparams", "on");
    model.result("pg2").feature("arwl1").set("arrowcount", 20);
    model.result("pg2").feature("arwl1").set("arrowlength", "normalized");
    model.result("pg2").feature("arwl1").set("showsolutionparams", "on");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("col1", "Color");
    model.result("pg2").feature("arwl1").feature("col1").set("showcolordata", "off");
    model.result("pg2").feature("arwl1").feature("col1").set("expr", "abs(u)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (nipfl)");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "0.5*nipfl.dh");
    model.result("pg3").feature("line1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "parent");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("line1").set("unit", "degC");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u96f7\u8bfa\u6570");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "nipfl.Re");
    model.result("pg4").feature("line1").set("descr", "\u96f7\u8bfa\u6570");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").run();

    model.title("\u6c60\u5858\u5faa\u73af\u7cfb\u7edf\u7684\u5730\u70ed\u4f9b\u6696");

    model
         .description("\u6c60\u5858\u548c\u6e56\u6cca\u53ef\u4ee5\u5145\u5f53\u4f7f\u7528\u70ed\u6cf5\u8fdb\u884c\u7684\u5730\u70ed\u4f9b\u6696\u5e94\u7528\u4e2d\u7684\u70ed\u5e93\u3002\u5728\u8be5\u793a\u4f8b\u4e2d\uff0c\u6c60\u5858\u4e2d\u7684\u6c34\u901a\u8fc7\u805a\u4e59\u70ef\u7ba1\u9053\u5728\u4e00\u4e2a\u5c01\u95ed\u7cfb\u7edf\u4e2d\u5faa\u73af\uff0c\u7ba1\u9053\u5206\u7ec4\u7f20\u7ed5\u5728\u6ed1\u677f\u4e0a\uff0c\u5448\u7ebd\u5e26\u72b6\u3002\u201c\u975e\u7b49\u6e29\u7ba1\u9053\u6d41\u201d\u63a5\u53e3\u53ef\u7528\u4e8e\u8bbe\u7f6e\u548c\u6c42\u89e3\u6e29\u5ea6\u5206\u5e03\u3001\u538b\u964d\u3001\u901f\u5ea6\u5206\u5e03\u548c\u70ed\u4ea4\u6362\u95ee\u9898\u3002");

    model.label("geothermal_heating.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
