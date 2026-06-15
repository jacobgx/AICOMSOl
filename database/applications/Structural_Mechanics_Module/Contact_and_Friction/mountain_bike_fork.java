/*
 * mountain_bike_fork.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:03 by COMSOL 6.3.0.290. */
public class mountain_bike_fork {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "mountain_bike_fork.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("frame", "material");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").pair("ap2").manualSelection(true);
    model.component("comp1").pair("ap2").type("Contact");
    model.component("comp1").pair("ap2").mapping("initial");
    model.component("comp1").pair("ap3").swap();

    model.param().set("n", "0");
    model.param().descr("n", "\u8fc7\u76c8\u914d\u5408\u4e58\u6570");
    model.param().set("mu", "0.2");
    model.param().descr("mu", "\u6469\u64e6\u7cfb\u6570");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94a2");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.29"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7800"});

    model.component("comp1").physics("solid").feature("dcnt1").set("destination_offset", "(0.04[mm])*n");
    model.component("comp1").physics("solid").feature("dcnt1").set("zeroInitGap", true);
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 2);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", "mu");
    model.component("comp1").physics("solid").feature("dcnt1").create("stb1", "ContactStabilization", 2);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(191);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection()
         .set(1, 10, 13, 18, 163, 168, 171, 176, 179, 183, 185, 187, 189, 196, 198, 200, 202, 204);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(166);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(367, 370, 375, 378);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(366);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis3", "dis2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(374);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(166);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("targetface").set(165);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(174);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(385, 388, 393, 396);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(384);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("elemcount", 6);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map2").feature().duplicate("dis3", "dis2");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").selection().set(392);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftet2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("dis1").selection().set(27, 28, 31, 33);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("ftet2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet2").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size2").selection().set(109, 110);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size2").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("hmin", 0.011);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "n", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "n", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0.1, range(0.25, 0.25, 1.0)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("view", "view2");
    model.result("pg1").run();

    model.view("view2").set("locked", true);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u7b49\u6548\u5e94\u529b\uff0c\u7b49\u503c\u9762");
    model.result("pg2").set("data", "mir1");
    model.result("pg2").create("iso1", "Isosurface");
    model.result("pg2").feature("iso1").set("expr", "solid.misesGp");
    model.result("pg2").feature("iso1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg2").feature("iso1").set("levelmethod", "levels");
    model.result("pg2").feature("iso1").set("levels", "range(100,50,400)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u7b2c\u4e00\u4e3b\u5e94\u529b\uff08yz \u5e73\u9762\uff09");
    model.result("pg3").set("data", "mir1");
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("expr", "solid.sp1Gp");
    model.result("pg3").feature("slc1").set("descr", "\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg3").feature("slc1").set("quickxmethod", "coord");
    model.result("pg3").feature("slc1").set("quickx", "range(-0.03,0.01,0.03)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7b2c\u4e00\u4e3b\u5e94\u529b\uff08zx \u5e73\u9762\uff09");
    model.result("pg4").run();
    model.result("pg4").feature("slc1").set("quickx", "range(-0.0215,0.01,0.0385)");
    model.result("pg4").feature("slc1").set("quickplane", "zx");
    model.result("pg4").run();
    model.result("pg4").feature("slc1").set("quickymethod", "coord");
    model.result("pg4").feature("slc1").set("quicky", "range(-0.0215,0.01,0.0385)");
    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u7b2c\u4e09\u4e3b\u5e94\u529b\uff08yz \u5e73\u9762\uff09");
    model.result("pg5").run();
    model.result("pg5").feature("slc1").set("expr", "solid.sp3Gp");
    model.result("pg5").feature("slc1").set("descr", "\u7b2c\u4e09\u4e3b\u5e94\u529b");
    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").label("\u7b2c\u4e09\u4e3b\u5e94\u529b\uff08zx \u5e73\u9762\uff09");
    model.result("pg6").run();
    model.result("pg6").feature("slc1").set("expr", "solid.sp3Gp");
    model.result("pg6").feature("slc1").set("descr", "\u7b2c\u4e09\u4e3b\u5e94\u529b");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u63a5\u89e6\u538b\u529b");
    model.result("pg7").set("data", "mir1");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "solid.Tn");
    model.result("pg7").feature("surf1").set("descr", "\u63a5\u89e6\u538b\u529b");

    model.view("view2").set("locked", false);

    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("\u53ef\u4f20\u9012\u8f7d\u8377");
    model.result().numerical("int1").selection().set(172, 178);
    model.result().numerical("int1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("int1").setIndex("expr", "2*sqrt(x^2+(y-0.0085)^2)*mu*solid.Tn", 0);
    model.result().numerical("int1").setIndex("descr", "\u53ef\u4f20\u9012\u626d\u77e9", 0);
    model.result().numerical("int1").setIndex("expr", "2*mu*solid.Tn", 1);
    model.result().numerical("int1").setIndex("descr", "\u53ef\u4f20\u9012\u529b", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u53ef\u4f20\u9012\u8f7d\u8377");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result("pg2").run();

    model.title("\u5c71\u5730\u81ea\u884c\u8f66\u524d\u53c9\u7684\u8fc7\u76c8\u914d\u5408\u8fde\u63a5");

    model
         .description("\u8fc7\u76c8\u8fde\u63a5\u662f\u4e00\u79cd\u5c06\u4e24\u4e2a\u96f6\u4ef6\u8fde\u63a5\u5728\u4e00\u8d77\u7684\u6280\u672f\uff0c\u4e00\u4e2a\u5185\u90e8\u96f6\u4ef6\u51b7\u5374\u6536\u7f29\u540e\uff0c\u4e0e\u53e6\u4e00\u4e2a\u96f6\u4ef6\u5f62\u6210\u914d\u5408\u8fde\u63a5\u3002\u5f53\u8be5\u96f6\u4ef6\u518d\u6b21\u53d7\u70ed\u81a8\u80c0\u65f6\uff0c\u4f1a\u5728\u8fd9\u4e24\u4e2a\u96f6\u4ef6\u7684\u754c\u9762\u4ea7\u751f\u4e00\u4e2a\u63a5\u89e6\u538b\u529b\u3002\n\n\u5728\u8fd9\u4e2a\u5c71\u5730\u81ea\u884c\u8f66\u524d\u53c9\u6559\u5b66\u6848\u4f8b\u4e2d\u6a21\u62df\u4e86\u8fd9\u7c7b\u8fde\u63a5\uff0c\u5176\u4e2d\u8f6c\u5411\u7ba1\u8fde\u63a5\u5230\u80a9\u76d6\u3002\u4eff\u771f\u7814\u7a76\u4e86\u63a5\u89e6\u538b\u529b\u548c\u5e94\u529b\u5206\u5e03\uff0c\u4ee5\u53ca\u53ef\u4f20\u9012\u7684\u529b\u548c\u626d\u77e9\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("mountain_bike_fork.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
