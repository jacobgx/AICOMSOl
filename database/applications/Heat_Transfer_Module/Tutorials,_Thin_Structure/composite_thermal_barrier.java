/*
 * composite_thermal_barrier.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:35 by COMSOL 6.3.0.290. */
public class composite_thermal_barrier {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Thin_Structure");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.param().set("d_ceram1", "50[um]");
    model.param().descr("d_ceram1", "\u5c42 1 \u7684\u539a\u5ea6");
    model.param().set("d_ceram2", "75[um]");
    model.param().descr("d_ceram2", "\u5c42 2 \u7684\u539a\u5ea6");
    model.param().set("T_hot", "1220[degC]");
    model.param().descr("T_hot", "\u9ad8\u6e29");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 4);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "d_ceram1");
    model.component("comp1").geom("geom1").feature("cyl2")
         .set("pos", new String[]{"0", "0", "2-(d_ceram1+d_ceram2)/2"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "d_ceram2");
    model.component("comp1").geom("geom1").feature("cyl3")
         .set("pos", new String[]{"0", "0", "2-(d_ceram1+d_ceram2)/2+d_ceram1"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new int[]{0, -2, 4});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new int[]{0, 2, 4});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(2, 3);
    model.component("comp1").cpl("intop1").set("opname", "intopBarrier");
    model.component("comp1").cpl("intop1").label("\u79ef\u5206\uff1a\u4fdd\u6e29\u5c42");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().set(2);
    model.component("comp1").cpl("intop2").label("\u79ef\u5206\uff1a\u5c42 1");
    model.component("comp1").cpl("intop2").set("opname", "intopLayer1");
    model.component("comp1").cpl().duplicate("intop3", "intop1");
    model.component("comp1").cpl("intop3").selection().set(3);
    model.component("comp1").cpl("intop3").label("\u79ef\u5206\uff1a\u5c42 2");
    model.component("comp1").cpl("intop3").set("opname", "intopLayer2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("vol_barrier", "intopBarrier(1)");
    model.component("comp1").variable("var1").descr("vol_barrier", "\u4fdd\u6e29\u5c42\u7684\u4f53\u79ef");
    model.component("comp1").variable("var1").set("vol_layer1", "intopLayer1(1)");
    model.component("comp1").variable("var1").descr("vol_layer1", "\u5c42 1 \u7684\u4f53\u79ef");
    model.component("comp1").variable("var1").set("vol_layer2", "intopLayer2(1)");
    model.component("comp1").variable("var1").descr("vol_layer2", "\u5c42 2 \u7684\u4f53\u79ef");
    model.component("comp1").variable("var1").set("T_int_barrier", "intopBarrier(T)");
    model.component("comp1").variable("var1")
         .descr("T_int_barrier", "\u4fdd\u6e29\u5c42\u7684\u6e29\u5ea6\u79ef\u5206");
    model.component("comp1").variable("var1").set("T_int_layer1", "intopLayer1(T)");
    model.component("comp1").variable("var1").descr("T_int_layer1", "\u5c42 1 \u7684\u6e29\u5ea6\u79ef\u5206");
    model.component("comp1").variable("var1").set("T_int_layer2", "intopLayer2(T)");
    model.component("comp1").variable("var1").descr("T_int_layer2", "\u5c42 2 \u7684\u6e29\u5ea6\u79ef\u5206");
    model.component("comp1").variable("var1").set("T_ave_barrier", "intopBarrier(T)/intopBarrier(1)");
    model.component("comp1").variable("var1")
         .descr("T_ave_barrier", "\u4fdd\u6e29\u5c42\u7684\u5e73\u5747\u6e29\u5ea6");
    model.component("comp1").variable("var1").set("T_ave_layer1", "intopLayer1(T)/intopLayer1(1)");
    model.component("comp1").variable("var1").descr("T_ave_layer1", "\u5c42 1 \u7684\u5e73\u5747\u6e29\u5ea6");
    model.component("comp1").variable("var1").set("T_ave_layer2", "intopLayer2(T)/intopLayer2(1)");
    model.component("comp1").variable("var1").descr("T_ave_layer2", "\u5c42 2 \u7684\u5e73\u5747\u6e29\u5ea6");
    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff1a\u4fdd\u6e29\u5c42\u7684\u6e29\u5ea6");

    model.component("comp1").material().create("matlnk1", "Link");
    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").label("Steel AISI 4340");
    model.material("mat1").set("family", "steel");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("matlnk1").set("link", "mat1");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().set(2);
    model.material().create("mat2", "Common", "");
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.material("mat2").label("\u9676\u74f7 1");
    model.material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"1[W/(m*K)]"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"6000[kg/m^3]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"320[J/(kg*K)]"});
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").selection().set(3);
    model.material().create("mat3", "Common", "");
    model.component("comp1").material("matlnk3").set("link", "mat3");
    model.material("mat3").label("\u9676\u74f7 2");
    model.material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"0.5[W/(m*K)]"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"5800[kg/m^3]"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"280[J/(kg*K)]"});

    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(3);
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp2").selection().set(13);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "T_hot");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(13, 18);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").label("\u6e29\u5ea6\uff0c\u4e09\u7ef4\u65b9\u6cd5");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6e29\u5ea6\u66f2\u7ebf");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u9ad8\u5ea6 (cm)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6e29\u5ea6\u66f2\u7ebf");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(15, 17, 19, 21);
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "z");
    model.result("pg2").feature("lngr1").set("linewidth", 2);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u6e29\u5ea6\uff0c\u4e09\u7ef4\u65b9\u6cd5", 0);
    model.result("pg2").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").physics().create("ht2", "HeatTransfer", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ht2", false);

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht2", true);

    model.component("comp2").geom("geom2").lengthUnit("cm");
    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", 2);
    model.component("comp2").geom("geom2").feature("cyl1").set("h", 4);
    model.component("comp2").geom("geom2").feature("cyl1").setIndex("layer", 2, 0);
    model.component("comp2").geom("geom2").feature("cyl1").set("layerside", false);
    model.component("comp2").geom("geom2").feature("cyl1").set("layerbottom", true);
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").run("cyl1");
    model.component("comp2").geom("geom2").create("ls1", "LineSegment");
    model.component("comp2").geom("geom2").feature("ls1").set("specify1", "coord");
    model.component("comp2").geom("geom2").feature("ls1").set("coord1", new int[]{0, -2, 4});
    model.component("comp2").geom("geom2").feature("ls1").set("specify2", "coord");
    model.component("comp2").geom("geom2").feature("ls1").set("coord2", new int[]{0, 2, 4});
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").view("view2").set("transparency", false);

    model.component("comp2").cpl().create("intop4", "Integration");
    model.component("comp2").cpl("intop4").set("axisym", true);
    model.component("comp2").cpl("intop4").label("\u79ef\u5206\uff1a\u8fb9\u754c");
    model.component("comp2").cpl("intop4").set("opname", "intopBnd");
    model.component("comp2").cpl("intop4").selection().geom("geom2", 2);
    model.component("comp2").cpl("intop4").selection().set(6);

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("isLayer1", "llmat1_xdim.atonly(dom==1)");
    model.component("comp2").variable("var2")
         .descr("isLayer1", "\u5c42 1 \u7684\u6807\u8bc6\u7b26\uff08=1\uff1a\u5728\u5c42 1 \u4e0a\uff0c0\uff1a\u5728\u5176\u4ed6\u5c42\u4e0a\uff09");
    model.component("comp2").variable("var2").set("isLayer2", "llmat1_xdim.atonly(dom==2)");
    model.component("comp2").variable("var2")
         .descr("isLayer2", "\u5c42 2 \u7684\u6807\u8bc6\u7b26\uff08=1\uff1a\u5728\u5c42 2 \u4e0a\uff0c0\uff1a\u5728\u5176\u4ed6\u5c42\u4e0a\uff09");
    model.component("comp2").variable("var2")
         .set("vol_barrier", "intopBnd(ht2.sls2.xdintopall(1))", "\u4fdd\u6e29\u5c42\u7684\u4f53\u79ef");
    model.component("comp2").variable("var2")
         .set("vol_layer1", "intopBnd(ht2.sls2.xdintopall(isLayer1))", "\u5c42 1 \u7684\u4f53\u79ef");
    model.component("comp2").variable("var2")
         .set("vol_layer2", "intopBnd(ht2.sls2.xdintopall(isLayer2))", "\u5c42 2 \u7684\u4f53\u79ef");
    model.component("comp2").variable("var2")
         .set("T_int_barrier", "intopBnd(ht2.sls2.xdintopall(T2))", "\u4fdd\u6e29\u5c42\u7684\u6e29\u5ea6\u79ef\u5206");
    model.component("comp2").variable("var2")
         .set("T_int_layer1", "intopBnd(ht2.sls2.xdintopall(T2*isLayer1))", "\u5c42 1 \u7684\u6e29\u5ea6\u79ef\u5206");
    model.component("comp2").variable("var2")
         .set("T_int_layer2", "intopBnd(ht2.sls2.xdintopall(T2*isLayer2))", "\u5c42 2 \u7684\u6e29\u5ea6\u79ef\u5206");
    model.component("comp2").variable("var2")
         .set("T_ave_barrier", "T_int_barrier/vol_barrier", "\u4fdd\u6e29\u5c42\u7684\u5e73\u5747\u6e29\u5ea6");
    model.component("comp2").variable("var2")
         .set("T_ave_layer1", "T_int_layer1/vol_layer1", "\u5c42 1 \u7684\u5e73\u5747\u6e29\u5ea6");
    model.component("comp2").variable("var2")
         .set("T_ave_layer2", "T_int_layer2/vol_layer2", "\u5c42 2 \u7684\u5e73\u5747\u6e29\u5ea6");
    model.component("comp2").variable("var2").label("\u53d8\u91cf\uff1a\u4fdd\u6e29\u5c42\u7684\u6e29\u5ea6");

    model.component("comp2").material().create("matlnk4", "Link");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("link", "mat2", 0);
    model.material("lmat1").setIndex("thickness", "d_ceram1", 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat2", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "d_ceram1", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat2", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "d_ceram1", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("link", "mat3", 1);
    model.material("lmat1").setIndex("thickness", "d_ceram2", 1);
    model.component("comp2").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp2").material("llmat1").selection().set(6);

    model.component("comp2").physics("ht2").create("sls1", "SolidLayeredShell", 2);
    model.component("comp2").physics("ht2").feature("sls1").selection().set(6);
    model.component("comp2").physics("ht2").create("sls2", "SolidLayeredShell", 2);
    model.component("comp2").physics("ht2").feature("sls2").selection().set(6);
    model.component("comp2").physics("ht2").feature("sls2").set("LayerType", "General");
    model.component("comp2").physics("ht2").create("temp1", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht2").feature("temp1").selection().set(3);
    model.component("comp2").physics("ht2").create("temp2", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht2").feature("temp2").selection().set(7);
    model.component("comp2").physics("ht2").feature("temp2").set("T0", "T_hot");

    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"ht2/sls2"});

    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().set(7, 10);
    model.component("comp2").mesh("mesh2").run("ftri1");
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset3");
    model.result().dataset("lshl1").selection().geom("geom2", 2);
    model.result().dataset("lshl1").selection().set(6);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht2)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").selection().geom("geom2", 3);
    model.result("pg3").selection().set(1, 2);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").label("\u57df");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg3").feature().create("vol2", "Volume");
    model.result("pg3").feature("vol2").label("\u591a\u5c42\u58f3");
    model.result("pg3").feature("vol2").set("data", "lshl1");
    model.result("pg3").feature("vol2").set("showsolutionparams", "on");
    model.result("pg3").feature("vol2").set("solutionparams", "parent");
    model.result("pg3").feature("vol2").set("titletype", "none");
    model.result("pg3").feature("vol2").set("smooth", "internal");
    model.result("pg3").feature("vol2").set("showsolutionparams", "on");
    model.result("pg3").feature("vol2").set("data", "lshl1");
    model.result("pg3").feature("vol2").set("inheritplot", "vol1");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg3").feature("line1").set("data", "lshl1");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("solutionparams", "parent");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("titletype", "none");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "fromtheme");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "lshl1");
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr2").set("linewidth", "preference");
    model.result("pg2").feature("lngr2").set("data", "dset3");
    model.result("pg2").feature("lngr2").selection().set(9, 11);
    model.result("pg2").feature("lngr2").set("expr", "T2");
    model.result("pg2").feature("lngr2").set("descr", "\u6e29\u5ea6");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "z");
    model.result("pg2").feature("lngr2").set("linestyle", "none");
    model.result("pg2").feature("lngr2").set("linecolor", "magenta");
    model.result("pg2").feature("lngr2").set("linemarker", "cycle");
    model.result("pg2").feature("lngr2").set("markerpos", "interp");
    model.result("pg2").feature("lngr2").set("markers", 15);
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u6e29\u5ea6\uff0c\u4e8c\u7ef4\u65b9\u6cd5", 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6\uff0c\u4e8c\u7ef4\u65b9\u6cd5");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"ht2/sls1"});
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("lshl2", "LayeredMaterial");
    model.result().dataset("lshl2").set("data", "dset5");
    model.result().dataset("lshl2").selection().geom("geom2", 2);
    model.result().dataset("lshl2").selection().set(6);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (ht2)");
    model.result("pg4").set("data", "dset5");
    model.result("pg4").selection().geom("geom2", 3);
    model.result("pg4").selection().set(1, 2);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").label("\u57df");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").feature().create("vol2", "Volume");
    model.result("pg4").feature("vol2").label("\u591a\u5c42\u58f3");
    model.result("pg4").feature("vol2").set("data", "lshl2");
    model.result("pg4").feature("vol2").set("showsolutionparams", "on");
    model.result("pg4").feature("vol2").set("solutionparams", "parent");
    model.result("pg4").feature("vol2").set("titletype", "none");
    model.result("pg4").feature("vol2").set("smooth", "internal");
    model.result("pg4").feature("vol2").set("showsolutionparams", "on");
    model.result("pg4").feature("vol2").set("data", "lshl2");
    model.result("pg4").feature("vol2").set("inheritplot", "vol1");
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg4").feature("line1").set("data", "lshl2");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("solutionparams", "parent");
    model.result("pg4").feature("line1").set("expr", "1");
    model.result("pg4").feature("line1").set("titletype", "none");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "fromtheme");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "lshl2");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").create("lngr3", "LineGraph");
    model.result("pg2").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr3").set("linewidth", "preference");
    model.result("pg2").feature("lngr3").set("data", "dset5");
    model.result("pg2").feature("lngr3").selection().set(9, 11);
    model.result("pg2").feature("lngr3").set("expr", "T2");
    model.result("pg2").feature("lngr3").set("xdata", "expr");
    model.result("pg2").feature("lngr3").set("xdataexpr", "z");
    model.result("pg2").feature("lngr3").set("linestyle", "none");
    model.result("pg2").feature("lngr3").set("linecolor", "fromtheme");
    model.result("pg2").feature("lngr3").set("linewidth", 2);
    model.result("pg2").feature("lngr3").set("linemarker", "triangle");
    model.result("pg2").feature("lngr3").set("markerpos", "interp");
    model.result("pg2").feature("lngr3").set("markers", 20);
    model.result("pg2").feature("lngr3").set("legend", true);
    model.result("pg2").feature("lngr3").set("legendmethod", "manual");
    model.result("pg2").feature("lngr3")
         .setIndex("legends", "\u6e29\u5ea6\uff0c\u4e8c\u7ef4\u989d\u5916\u7ef4\u5ea6\u65b9\u6cd5", 0);
    model.result("pg2").run();
    model.result("pg4").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u4e8c\u7ef4\u989d\u5916\u7ef4\u5ea6\u65b9\u6cd5");

    model.result("pg2").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grp2").add("plotgroup", "pg2");
    model.nodeGroup("grp2").label("\u4e0d\u540c\u65b9\u6cd5\u7684\u6bd4\u8f83");

    model.result().create("pg5", "PlotGroup3D");

    model.nodeGroup("grp1").add("plotgroup", "pg5");

    model.result("pg5").run();
    model.result("pg5").label("\u6e29\u5ea6\uff08\u5c42\u8868\u9762\uff09");
    model.result("pg5").set("data", "lshl2");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").run();
    model.result().dataset("lshl2").set("scale", 20);
    model.result("pg5").run();
    model.result("pg5").set("view", "new");
    model.result().create("pg6", "PlotGroup3D");

    model.nodeGroup("grp1").add("plotgroup", "pg6");

    model.result("pg6").run();
    model.result("pg6").label("\u6e29\u5ea6\uff08\u5207\u9762\uff09");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("view", "new");
    model.result("pg6").create("lss1", "LayeredMaterialSlice");
    model.result("pg6").feature("lss1").set("locdef", "interfaces");
    model.result("pg6").feature("lss1").set("slicedisplacement", "linear");
    model.result("pg6").feature("lss1").set("orientationlinear", "x");
    model.result("pg6").feature("lss1").set("xseparation", 0.25);
    model.result("pg6").feature("lss1").set("showdescriptions", true);
    model.result("pg6").feature("lss1").set("descriptionseparation", 1);
    model.result("pg6").feature("lss1").set("colortable", "HeatCameraLight");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");

    model.nodeGroup("grp2").add("plotgroup", "pg7");

    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6\uff08\u539a\u5ea6\u65b9\u5411\uff09");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().set(4, 7);
    model.result("pg7").feature("lngr1").set("expr", "z-(2[cm]-(d_ceram1+d_ceram2)/2)");
    model.result("pg7").feature("lngr1").set("unit", "m");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").run();
    model.result("pg7").create("thr1", "ThroughThickness");
    model.result("pg7").feature("thr1").set("markerpos", "datapoints");
    model.result("pg7").feature("thr1").set("linewidth", "preference");
    model.result("pg7").feature("thr1").set("data", "dset5");
    model.result("pg7").feature("thr1").selection().set(2);
    model.result("pg7").feature("thr1").set("expr", "T2");
    model.result("pg7").feature("thr1").set("linestyle", "none");
    model.result("pg7").feature("thr1").set("linemarker", "cycle");
    model.result("pg7").feature("thr1").set("markerpos", "interp");
    model.result("pg7").feature("thr1").set("includeinterfaces", "all");
    model.result("pg7").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"T_ave_barrier"});
    model.result().numerical("gev1").set("descr", new String[]{"\u4fdd\u6e29\u5c42\u7684\u5e73\u5747\u6e29\u5ea6"});
    model.result().numerical("gev1").set("unit", new String[]{"K"});
    model.result().numerical("gev1").set("expr", new String[]{"T_ave_barrier", "T_ave_layer1"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u4fdd\u6e29\u5c42\u7684\u5e73\u5747\u6e29\u5ea6", "\u5c42 1 \u7684\u5e73\u5747\u6e29\u5ea6"});
    model.result().numerical("gev1").set("expr", new String[]{"T_ave_barrier", "T_ave_layer1", "T_ave_layer2"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u4fdd\u6e29\u5c42\u7684\u5e73\u5747\u6e29\u5ea6", "\u5c42 1 \u7684\u5e73\u5747\u6e29\u5ea6", "\u5c42 2 \u7684\u5e73\u5747\u6e29\u5ea6"});

    return model;
  }

  public static Model run2(Model model) {
    model.result().numerical("gev1").setIndex("unit", "degC", 0);
    model.result().numerical("gev1").setIndex("unit", "degC", 1);
    model.result().numerical("gev1").setIndex("unit", "degC", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset5");
    model.result().numerical("gev2").set("expr", new String[]{"T_ave_barrier"});
    model.result().numerical("gev2").set("descr", new String[]{"\u4fdd\u6e29\u5c42\u7684\u5e73\u5747\u6e29\u5ea6"});
    model.result().numerical("gev2").set("unit", new String[]{"K"});
    model.result().numerical("gev2").set("expr", new String[]{"T_ave_barrier", "T_ave_layer1"});
    model.result().numerical("gev2")
         .set("descr", new String[]{"\u4fdd\u6e29\u5c42\u7684\u5e73\u5747\u6e29\u5ea6", "\u5c42 1 \u7684\u5e73\u5747\u6e29\u5ea6"});
    model.result().numerical("gev2").set("expr", new String[]{"T_ave_barrier", "T_ave_layer1", "T_ave_layer2"});
    model.result().numerical("gev2")
         .set("descr", new String[]{"\u4fdd\u6e29\u5c42\u7684\u5e73\u5747\u6e29\u5ea6", "\u5c42 1 \u7684\u5e73\u5747\u6e29\u5ea6", "\u5c42 2 \u7684\u5e73\u5747\u6e29\u5ea6"});
    model.result().numerical("gev2").setIndex("unit", "degC", 0);
    model.result().numerical("gev2").setIndex("unit", "degC", 1);
    model.result().numerical("gev2").setIndex("unit", "degC", 2);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg1").run();

    model.title("\u590d\u5408\u4fdd\u6e29\u5c42");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u4e24\u79cd\u4e0d\u540c\u65b9\u6cd5\u6765\u8bbe\u7f6e\u5177\u6709\u4e0d\u540c\u70ed\u5bfc\u7387\u7684\u8584\u5939\u5c42\u7ed3\u6784\u3002\n\u9996\u5148\uff0c\u4ee5\u4e09\u7ef4\u5bf9\u8c61\u5bf9\u8fd9\u79cd\u590d\u5408\u7ed3\u6784\u5efa\u6a21\u3002\n\u5728\u7b2c\u4e8c\u79cd\u65b9\u6cd5\u4e2d\uff0c\u91c7\u7528\u4e86\u201c\u8584\u5c42\u201d\u8fb9\u754c\u6761\u4ef6\u6765\u6a21\u62df\uff0c\u4ece\u800c\u907f\u514d\u5bf9\u8584\u6c42\u89e3\u57df\u7684\u89e3\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("composite_thermal_barrier.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
