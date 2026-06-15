/*
 * polynomial_hyperelastic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:32 by COMSOL 6.3.0.290. */
public class polynomial_hyperelastic {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("kappa", "3[MPa]");
    model.param().descr("kappa", "\u4f53\u79ef\u6a21\u91cf");
    model.param().set("C01", "0.5[MPa]");
    model.param().descr("C01", "\u591a\u9879\u5f0f\u7cfb\u6570 C01");
    model.param().set("C10", "0.1[MPa]");
    model.param().descr("C10", "\u591a\u9879\u5f0f\u7cfb\u6570 C10");
    model.param().set("C11", "0.15[MPa]");
    model.param().descr("C11", "\u591a\u9879\u5f0f\u7cfb\u6570 C11");
    model.param().set("C20", "0.2[MPa]");
    model.param().descr("C20", "\u591a\u9879\u5f0f\u7cfb\u6570 C20");
    model.param().set("C02", "-0.2[MPa]");
    model.param().descr("C02", "\u591a\u9879\u5f0f\u7cfb\u6570 C02");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.1, 0.05, 0.02});
    model.component("comp1").geom("geom1").feature("blk1").set("rot", -90);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Wsiso_MR2", "C10*(solid.I1CIel-3)+C01*(solid.I2CIel-3)");
    model.component("comp1").variable("var1")
         .descr("Wsiso_MR2", "\u7b49\u4f53\u79ef\u5e94\u53d8\u80fd\u5bc6\u5ea6\uff0cMooney\u2013Rivlin \u4e24\u53c2\u6570");
    model.component("comp1").variable("var1")
         .set("Wsiso_MR5", "Wsiso_MR2+C20*(solid.I1CIel-3)^2+C02*(solid.I2CIel-3)^2+C11*(solid.I1CIel-3)*(solid.I2CIel-3)");
    model.component("comp1").variable("var1")
         .descr("Wsiso_MR5", "\u7b49\u4f53\u79ef\u5e94\u53d8\u80fd\u5bc6\u5ea6\uff0cMooney\u2013Rivlin \u4e94\u53c2\u6570");
    model.component("comp1").variable("var1").set("Wsvol", "0.5*kappa*(solid.Jel-1)^2");
    model.component("comp1").variable("var1").descr("Wsvol", "\u4f53\u79ef\u5e94\u53d8\u80fd\u5bc6\u5ea6");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(5);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 3);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(2);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "-1[MPa]", "0"});
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm1").label("\u591a\u9879\u5f0f\uff0c\u4e24\u53c2\u6570");
    model.component("comp1").physics("solid").feature("hmm1").selection().all();
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "userDefined");
    model.component("comp1").physics("solid").feature("hmm1").set("Compressibility_UserDef", "NearlyIncompressible");
    model.component("comp1").physics("solid").feature("hmm1").set("Wsiso", "Wsiso_MR2");
    model.component("comp1").physics("solid").feature("hmm1").set("Uvol", "Wsvol");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(6, 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(7, 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 15);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("solid").create("hmm2", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm2").label("Mooney\u2013Rivlin");
    model.component("comp1").physics("solid").feature("hmm2").selection().all();
    model.component("comp1").physics("solid").feature("hmm2").set("MaterialModel", "MooneyRivlin");
    model.component("comp1").physics("solid").feature("hmm2").set("C10_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").set("C10", "C10");
    model.component("comp1").physics("solid").feature("hmm2").set("C01_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").set("C01", "C01");
    model.component("comp1").physics("solid").feature("hmm2").set("kappa", "kappa");
    model.component("comp1").physics("solid").feature().duplicate("hmm3", "hmm1");
    model.component("comp1").physics("solid").feature("hmm3").label("\u591a\u9879\u5f0f\uff0c\u4e94\u53c2\u6570");
    model.component("comp1").physics("solid").feature("hmm3").set("Wsiso", "Wsiso_MR5");

    model.study("std1").label("\u7814\u7a76\uff1a\u591a\u9879\u5f0f\uff0c\u4e24\u53c2\u6570");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/hmm2", "solid/hmm3"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
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
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff08\u591a\u9879\u5f0f\uff0c\u4e24\u53c2\u6570\uff09");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().numerical().create("max1", "MaxVolume");
    model.result().numerical("max1").selection().all();
    model.result().numerical("max1").set("expr", new String[]{"v"});
    model.result().numerical("max1").set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf"});
    model.result().numerical("max1").set("unit", new String[]{"m"});
    model.result().numerical("max1").set("obj", "abs");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4f53\u6700\u5927\u503c 1");
    model.result().numerical("max1").set("table", "tbl1");
    model.result().numerical("max1").setResult();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/hmm3"});
    model.study("std2").label("\u7814\u7a76\uff1aMooney\u2013Rivlin");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("vol1").feature("def").set("scale", "1");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b (Mooney\u2013Rivlin)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().numerical("max1").set("data", "dset2");
    model.result().numerical("max1").set("table", "tbl1");
    model.result().numerical("max1").appendResult();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u7814\u7a76\uff1a\u591a\u9879\u5f0f\uff0c\u4e94\u53c2\u6570");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("vol1").feature("def").set("scale", "1");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff08\u591a\u9879\u5f0f\uff0c\u4e94\u53c2\u6570\uff09");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().numerical("max1").set("data", "dset3");
    model.result().numerical("max1").set("table", "tbl1");
    model.result().numerical("max1").appendResult();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(2);
    model.result("pg4").feature("lngr1").set("expr", "solid.SGpYY");
    model.result("pg4").feature("lngr1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0cYY \u5206\u91cf");
    model.result("pg4").feature("lngr1").set("linemarker", "cycle");
    model.result("pg4").feature("lngr1").set("markerpos", "interp");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u591a\u9879\u5f0f\uff0c\u4e24\u53c2\u6570", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("data", "dset2");
    model.result("pg4").feature("lngr2").set("markers", 10);
    model.result("pg4").feature("lngr2").setIndex("legends", "Mooney\u2013Rivlin", 0);
    model.result("pg4").feature("lngr2").set("titletype", "none");
    model.result("pg4").run();
    model.result("pg3").run();

    model.title("\u591a\u9879\u5f0f\u8d85\u5f39\u6027\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u9610\u8ff0\u5982\u4f55\u901a\u8fc7\u7528\u6237\u5b9a\u4e49\u7684\u5e94\u53d8\u80fd\u5bc6\u5ea6\u6765\u5b9e\u73b0\u4e24\u9879\u548c\u4e94\u9879 Mooney-Rivlin \u672c\u6784\u6750\u6599\u6a21\u578b\u3002");

    model.label("polynomial_hyperelastic.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
