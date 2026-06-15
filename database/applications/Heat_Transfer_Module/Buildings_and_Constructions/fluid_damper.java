/*
 * fluid_damper.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:18 by COMSOL 6.3.0.290. */
public class fluid_damper {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Buildings_and_Constructions");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nitf1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Dr", "2.83e-2[m]", "\u6746\u76f4\u5f84");
    model.param().set("Dp", "8.37e-2[m]", "\u6d3b\u585e\u76f4\u5f84");
    model.param().set("Lp", "0.0254[m]", "\u6d3b\u585e\u534a\u957f");
    model.param().set("Dd", "0.1128[m]", "\u963b\u5c3c\u5668\u5916\u5f84");
    model.param().set("Hw", "1.37e-2[m]", "\u963b\u5c3c\u5668\u58c1\u539a");
    model.param().set("Ld", "U0+Lp", "\u963b\u5c3c\u5668\u5bc6\u5c01\u4ef6\u4f4d\u7f6e");
    model.param().set("U0", "0.1524[m]", "\u963b\u5c3c\u8154\u9ad8\u5ea6");
    model.param().set("T0", "300[K]", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("hwall", "5[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("a0", "U0-Lp", "\u6d3b\u585e\u4f4d\u79fb\u5927\u5c0f");
    model.param().set("f", "0.4[Hz]", "\u9891\u7387");
    model.param().set("ncycle", "16", "\u8f7d\u8377\u5faa\u73af\u6b21\u6570");
    model.param().set("tmax", "ncycle/f", "\u603b\u52a0\u8f7d\u65f6\u95f4");
    model.param().set("tstep", "0.1/f", "\u91c7\u6837\u65f6\u95f4\u95f4\u9694");

    model.func().create("an1", "Analytic");
    model.func("an1").set("expr", "a0*sin(2*pi*f*t)");
    model.func("an1").set("args", "t");
    model.func("an1").setIndex("argunit", "s", 0);
    model.func("an1").set("fununit", "m");
    model.func("an1").setIndex("plotargs", 40, 0, 2);
    model.func("an1").set("funcname", "zp");
    model.func("an1").label("\u6d3b\u585e\u4f4d\u79fb");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("zlin1", "zp(t)*(1+(Lp+Z)/(Ld-Lp))", "\u6d3b\u585e\u4e0b\u65b9\u7684\u7f51\u683c\u53d8\u5f62");
    model.component("comp1").variable("var1")
         .set("zlin2", "zp(t)*(Ld-Z)/(Ld-Lp)", "\u6d3b\u585e\u4e0a\u65b9\u7684\u7f51\u683c\u53d8\u5f62");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Dr/2", "2*Ld"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-Ld"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Dp/2", "2*Ld"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-Ld"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"Dd/2-Hw", "2*Ld"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "-Ld"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"Dd/2", "2*Ld"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "-Ld"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"Dd/2", "2*Lp"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"0", "-Lp"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("spf").selection().set(4, 6, 7, 8, 9);
    model.component("comp1").physics("ht").feature("fluid1").selection().set(4, 6, 7, 8, 9);
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7845\u6cb9");
    model.component("comp1").material("mat2").selection().set(4, 6, 7, 8, 9);
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").set("nu_25C", new String[]{"0.0125[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("VTC", new String[]{"0.6[1]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"22.5"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"950"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"2e3"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"nu_25C*rho*(1-VTC*(T-311[K])/(61[K]))/(1+VTC*0.2107)"});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2, 7, 9, 14, 16, 21, 23, 28);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(29, 30, 31);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "hwall");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T0");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(12);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(22, 24, 26);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "Manual");

    model.component("comp1").common().create("pres1", "PrescribedDeformation");
    model.component("comp1").common("pres1").selection().all();
    model.component("comp1").common("pres1").set("prescribedDeformation", new String[]{"0", "0", "zp(t)"});
    model.component("comp1").common("pres1").selection().set(2, 5, 8, 11);
    model.component("comp1").common().create("pres2", "PrescribedDeformation");
    model.component("comp1").common("pres2").set("prescribedDeformation", new String[]{"0", "0", "zlin1"});
    model.component("comp1").common("pres2").selection().set(1, 4, 7, 10);
    model.component("comp1").common().create("pres3", "PrescribedDeformation");
    model.component("comp1").common("pres3").set("prescribedDeformation", new String[]{"0", "0", "zlin2"});
    model.component("comp1").common("pres3").selection().set(3, 6, 9, 12);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(23, 25, 27, 28);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection()
         .set(1, 5, 8, 12, 15, 19, 22, 26, 29, 31);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 32);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(9, 11, 13, 14);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(16, 18, 20, 21);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 8);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").selection().set(3, 10, 17, 24, 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("numelem", 32);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis6", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").selection().set(2, 4, 6, 7);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords2", "Dd/2-Hw", 0);
    model.component("comp1").probe("pdom1").setIndex("coords2", "U0", 1);
    model.component("comp1").probe("pdom1").feature("ppb1").set("probename", "temppr");
    model.component("comp1").probe("pdom1").feature("ppb1").set("unit", "\u00b0F");

    model.study("std1").feature("time").set("tlist", "range(0,tstep,(ncycle-1)/f) range((ncycle-1)/f,tstep/2,tmax)");
    model.study("std1").feature("time").set("probefreq", "tout");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.U");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "spf.U");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "nitf1.T");
    model.result("pg6").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg6").feature("surf1").feature("sel1").selection().set(4, 6, 7, 8, 9);
    model.result("pg6").feature().create("surf2", "Surface");
    model.result("pg6").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg6").feature("surf2").set("showsolutionparams", "on");
    model.result("pg6").feature("surf2").set("solutionparams", "parent");
    model.result("pg6").feature("surf2").set("expr", "nitf1.T");
    model.result("pg6").feature("surf2").set("smooth", "internal");
    model.result("pg6").feature("surf2").set("showsolutionparams", "on");
    model.result("pg6").feature("surf2").set("data", "parent");
    model.result("pg6").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg6").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg6").feature("surf2").feature("sel1").selection().set(1, 2, 3, 5, 10, 11, 12);
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").feature().create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg6").feature("arws1").set("showsolutionparams", "on");
    model.result("pg6").feature("arws1").set("solutionparams", "parent");
    model.result("pg6").feature("arws1").set("expr", new String[]{"nitf1.ur", "nitf1.uz"});
    model.result("pg6").feature("arws1").set("xnumber", 30);
    model.result("pg6").feature("arws1").set("ynumber", 30);
    model.result("pg6").feature("arws1").set("arrowtype", "cone");
    model.result("pg6").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("arws1").set("showsolutionparams", "on");
    model.result("pg6").feature("arws1").set("data", "parent");
    model.result("pg6").feature("arws1").feature().create("col1", "Color");
    model.result("pg6").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg6").feature("arws1").feature("col1").set("expr", "spf.U");
    model.result("pg6").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg6").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 172, 0);
    model.result("pg7").label("\u52a8\u7f51\u683c");
    model.result("pg7").create("mesh1", "Mesh");
    model.result("pg7").feature("mesh1").set("meshdomain", "surface");
    model.result("pg7").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg7").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg7").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg7").feature("mesh1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.result("pg7").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg7").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg7").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg2").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0F", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("planetype", "general");
    model.result().dataset("cpl1").set("genmethod", "pointnormal");
    model.result().dataset("cpl1").set("genpnvec", new int[]{1, 0, 0});
    model.result().dataset().duplicate("cpl2", "cpl1");
    model.result().dataset("cpl2").set("genpnvec", new int[]{1, 1, 0});
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset1");
    model.result().dataset("rev2")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg2|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg3|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg4|ht"});
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg8").feature().create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("solutionparams", "parent");
    model.result("pg8").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg8").feature("vol1").set("smooth", "internal");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("data", "parent");
    model.result("pg8").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg8").run();
    model.result("pg8").label("\u6e29\u5ea6\u548c\u901f\u5ea6\u6d41\u7ebf");
    model.result("pg8").create("str1", "StreamlineSurface");
    model.result("pg8").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg8").feature("str1").set("data", "cpl1");
    model.result("pg8").feature("str1").set("solutionparams", "parent");
    model.result("pg8").feature("str1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg8").feature("str1")
         .set("descr", "\u901f\u5ea6\u573a \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("udist", 0.025);
    model.result("pg8").feature("str1").set("color", "black");
    model.result("pg8").feature().duplicate("str2", "str1");
    model.result("pg8").run();
    model.result("pg8").feature("str2").set("data", "cpl2");
    model.result("pg8").feature("str2").set("titletype", "none");
    model.result("pg8").run();
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").label("\u51b2\u7a0b\u7ed3\u675f\u4f4d\u7f6e\u7684\u5185\u58c1\u6e29\u5ea6");
    model.result("pg1").set("showlegends", false);
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6cbf\u5185\u58c1\u7684\u6e29\u5ea6");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").set("expr", "z/U0");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").selection().set(22, 24, 26);
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").run();
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u6cbf\u5185\u58c1\u7684\u6e29\u5ea6");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u65e0\u91cf\u7eb2\u9ad8\u5ea6 (1)");
    model.result("pg9").setIndex("looplevelinput", "manual", 0);
    model.result("pg9").setIndex("looplevel", new int[]{41, 172}, 0);
    model.result("pg9").run();
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").selection().set(4, 6, 7, 8, 9);
    model.result().numerical("av1").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("av1")
         .setIndex("looplevel", new int[]{162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172}, 0);
    model.result().numerical("av1").setIndex("expr", "spf.U", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl2");
    model.result().numerical("av1").setResult();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u6700\u540e\u4e00\u4e2a\u5faa\u73af\u7684\u5e73\u5747\u901f\u5ea6");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u6cbf\u5185\u58c1\u7684\u6e29\u5ea6");
    model.result("pg10").create("tblp1", "Table");
    model.result("pg10").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg10").feature("tblp1").set("linewidth", "preference");
    model.result("pg10").feature("tblp1").set("table", "tbl2");
    model.result("pg10").run();
    model.result("pg8").run();

    model.title("\u6d41\u4f53\u963b\u5c3c\u5668\u4e2d\u7684\u9ecf\u6027\u52a0\u70ed");

    model
         .description("\u6d41\u4f53\u963b\u5c3c\u5668\u5e7f\u6cdb\u7528\u4e8e\u519b\u4e8b\u9632\u9707\u88c5\u7f6e\u548c\u6c11\u7528\u5efa\u7b51\u7ed3\u6784\u7b49\u9886\u57df\uff0c\u8d77\u5230\u6291\u5236\u5730\u9707\u5f15\u8d77\u7684\u9707\u52a8\u548c\u98ce\u632f\u7684\u4f5c\u7528\uff1b\u5176\u5de5\u4f5c\u539f\u7406\u662f\u5c06\u673a\u68b0\u80fd\u8017\u6563\u6210\u70ed\u80fd\u3002\u672c\u4f8b\u6a21\u62df\u963b\u5c3c\u6d41\u4f53\u548c\u56fa\u4f53\u963b\u5c3c\u90e8\u4ef6\u4e2d\u7684\u9ecf\u6027\u52a0\u70ed\u4ee5\u53ca\u7531\u6b64\u4ea7\u751f\u7684\u5347\u6e29\u73b0\u8c61\u3002\u8be5\u6a21\u578b\u4f7f\u7528\u52a8\u7f51\u683c\u6765\u8868\u793a\u963b\u5c3c\u5668\u4e2d\u7684\u5927\u5e45\u5ea6\u8fd0\u52a8\u3002\u4eff\u771f\u7ed3\u679c\u4e0e\u5b9e\u9a8c\u6570\u636e\u9ad8\u5ea6\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("fluid_damper.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
