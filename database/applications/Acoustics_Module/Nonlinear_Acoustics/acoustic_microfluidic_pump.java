/*
 * acoustic_microfluidic_pump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class acoustic_microfluidic_pump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Nonlinear_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ta", true);
    model.study("std1").feature("freq").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").insertFile("acoustic_microfluidic_pump_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().label("\u53c2\u6570 - \u51e0\u4f55");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 - \u6a21\u578b");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("act_d0", "1[nm]", "\u9a71\u52a8\u4f4d\u79fb");
    model.param("par2").set("f0", "2[MHz]", "\u9a71\u52a8\u9891\u7387");
    model.param("par2").set("act_v0", "2*pi*f0*act_d0", "\u9a71\u52a8\u901f\u5ea6");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel1").all();

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

    model.component("comp1").physics("acpr").selection().set(1);
    model.component("comp1").physics("acpr").feature("fpam1").set("FluidModel", "Viscothermal");
    model.component("comp1").physics("acpr").create("tvb1", "ThermoviscousBoundaryLayerImpedance", 1);
    model.component("comp1").physics("acpr").feature("tvb1").selection().all();
    model.component("comp1").physics("acpr").feature("tvb1").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").create("tvb2", "ThermoviscousBoundaryLayerImpedance", 1);
    model.component("comp1").physics("acpr").feature("tvb2")
         .label("\u70ed\u9ecf\u6027\u8fb9\u754c\u5c42\u963b\u6297 - \u9a71\u52a8");
    model.component("comp1").physics("acpr").feature("tvb2").selection().set(1, 4, 111, 112);
    model.component("comp1").physics("acpr").feature("tvb2").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("tvb2").set("MechanicalCondition", "Velocity");
    model.component("comp1").physics("acpr").feature("tvb2").set("vel", new String[]{"act_v0", "0", "0"});
    model.component("comp1").physics("ta").selection().set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    model.component("comp1").physics("ta").prop("ShapeProperty").set("shapeorder_p", "2s");
    model.component("comp1").physics("ta").prop("ShapeProperty").set("shapeorder_u", "3s");
    model.component("comp1").physics("ta").prop("ShapeProperty").set("shapeorder_T", "3s");

    model.component("comp1").multiphysics().create("atb1", "AcousticThermoacousticBoundary", 1);
    model.component("comp1").multiphysics("atb1").selection().all();
    model.component("comp1").multiphysics().create("asdc1", "AcousticStreamingDomainCoupling", 2);
    model.component("comp1").multiphysics("asdc1").set("Source_physics", "ta");
    model.component("comp1").multiphysics("asdc1").selection().all();

    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(20);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection()
         .set(120, 124, 128, 132, 136, 140, 144, 148, 152, 156, 160, 164, 168);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 6);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.2E-6");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").feature("stat").setSolveFor("/multiphysics/atb1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (atb1)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "atb1.p_t");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").label("\u58f0\u538b (atb1)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u58f0\u4f53\u529b");
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().set(2);
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "sqrt(spf.F_acox^2+spf.F_acoy^2)");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("expr", "spf.U");
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").run();
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("unit", "mm/s");
    model.result("pg3").run();
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"u2", "v2"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.01);
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u901f\u5ea6 (spf) - \u5bf9\u6570");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", "1e-2");
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u58f0\u5b66\u5fae\u6d41\u4f53\u6cf5");

    model
         .description("\u672c\u6559\u7a0b\u4ecb\u7ecd\u4e86\u58f0\u5b66\u9a71\u52a8\u5fae\u6d41\u4f53\u6cf5\u7684\u4e8c\u7ef4\u6a21\u578b\u3002\u5728\u8fd9\u4e2a\u58f0\u5b66\u5fae\u6d41\u4f53\u6cf5\u4e2d\uff0c\u5fae\u6d41\u4f53\u901a\u9053\u7684\u5c16\u9510\u8fb9\u7f18\u4ea7\u751f\u58f0\u6d41\u9a71\u52a8\uff0c\u4f7f\u5f97\u6d41\u4f53\u56f4\u7ed5\u5c01\u95ed\u5fae\u6d41\u4f53\u901a\u9053\u73af\u8def\u6d41\u52a8\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u9891\u57df\u201d\u548c\u201c\u70ed\u9ecf\u6027\u58f0\u5b66\uff0c\u9891\u57df\u201d\uff08\u901a\u8fc7\u201c\u58f0-\u70ed\u9ecf\u6027\u58f0\u5b66\u8fb9\u754c\u201d\u591a\u7269\u7406\u573a\u7279\u5f81\u8fdb\u884c\u8026\u5408\uff09\u5bf9\u58f0\u573a\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u58f0\u6d41\uff0c\u57df\u8026\u5408\u201d\u591a\u7269\u7406\u573a\u7279\u5f81\u8ba1\u7b97\u58f0\u573a\u4ea7\u751f\u7684\u529b\uff0c\u5e76\u5c06\u5176\u5e94\u7528\u5230\u201c\u5c42\u6d41\u201d\u63a5\u53e3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("acoustic_microfluidic_pump.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
