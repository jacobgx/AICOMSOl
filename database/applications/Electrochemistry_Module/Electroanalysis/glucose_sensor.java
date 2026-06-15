/*
 * glucose_sensor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:52 by COMSOL 6.3.0.290. */
public class glucose_sensor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electroanalysis");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("c_glucose");
    model.component("comp1").physics("tcd").field("concentration")
         .component(new String[]{"c_glucose", "c_ferro", "c_ferri"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{100, 1000});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 12.5, 0);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("pt1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{4, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{25, 0});
    model.component("comp1").geom("geom1").run("arr1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("c_glucose_ext", "5[umol/L]", "\u5916\u90e8\u8461\u8404\u7cd6\u6d53\u5ea6");
    model.param().set("c_ferro_ext", "1[umol/L]", "\u6c30\u4e9a\u94c1\u9178\u76d0\u6d53\u5ea6");
    model.param().set("c_ferri_ext", "50[mmol/L]", "\u6c30\u94c1\u9178\u76d0\u6d53\u5ea6");
    model.param().set("V_max", "1.5e-5[mol/L/s]", "\u6700\u5927\u53cd\u5e94\u901f\u7387");
    model.param().set("Km", "0.5[mmol/L]", "\u7c73\u6c0f\u5e38\u6570");
    model.param().set("i0ref", "9.6485e7[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").cpl().create("aveop1", "Average");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(5);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("R_MM", "V_max*c_glucose/(Km+c_glucose)");
    model.component("comp1").variable("var1").descr("R_MM", "\u8461\u8404\u7cd6\u53cd\u5e94\u901f\u7387");
    model.component("comp1").variable("var1").set("i_avg", "aveop1(tcd.itot)");
    model.component("comp1").variable("var1").descr("i_avg", "\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").selection().set(5);
    model.component("comp1").physics("tcd").feature("es1").set("phisext0", 0.4);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", 1, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0ref");
    model.component("comp1").physics("tcd").create("es2", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es2").selection().set(2, 7);
    model.component("comp1").physics("tcd").feature("es2").set("BoundaryCondition", "CounterElectrode");
    model.component("comp1").physics("tcd").feature("es2").set("phisext0init", 0.1);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("Vi0", 1, 1);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("Vi0", -1, 2);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("i0_ref", "i0ref");
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(3);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_glucose_ext", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_ferro_ext", 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_ferri_ext", 2);
    model.component("comp1").physics("tcd").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tcd").feature("reac1").selection().set(1);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_c_glucose", "-R_MM", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_c_ferro", "R_MM", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_c_ferri", "-R_MM", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_glucose_ext", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_ferro_ext", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_ferri_ext", 2);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2, 4, 5, 6, 7);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "c_glucose_ext", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "mol/m^3", 0);
    model.study("std1").feature("stat").setIndex("pname", "c_glucose_ext", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "mol/m^3", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(50,50,1000)", 0);
    model.study("std1").feature("stat").setIndex("punit", "umol/L", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 20, 0);
    model.result("pg1").label("\u6d53\u5ea6, glucose (tcd)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"c_glucose"});
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tcd.tflux_c_glucosex", "tcd.tflux_c_glucosey"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 20, 0);
    model.result("pg2").label("\u6d53\u5ea6, ferro (tcd)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"c_ferro"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").set("typeintitle", true);
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"tcd.tflux_c_ferrox", "tcd.tflux_c_ferroy"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 20, 0);
    model.result("pg3").label("\u6d53\u5ea6, ferri (tcd)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"c_ferri"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tcd.tflux_c_ferrix", "tcd.tflux_c_ferriy"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("str1").active(false);
    model.result("pg2").run();
    model.result("pg2").set("solutionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "i_avg", 0);
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").run();

    model.title("\u8461\u8404\u7cd6\u4f20\u611f\u5668");

    model
         .description("\u7535\u5316\u5b66\u8461\u8404\u7cd6\u4f20\u611f\u5668\u91c7\u7528\u5b89\u57f9\u6cd5\u6765\u6d4b\u91cf\u6837\u54c1\u4e2d\u7684\u8461\u8404\u7cd6\u6d53\u5ea6\u3002\u672c\u4f8b\u6a21\u62df\u5728\u53c9\u6307\u7535\u6781\u4e0a\u65b9\u7684\u7535\u89e3\u8d28\u7684\u57fa\u672c\u5355\u5143\u4e2d\uff0c\u8461\u8404\u7cd6\u548c\u94c1/\u4e9a\u94c1\u6c30\u5316\u7269\u6c27\u5316\u8fd8\u539f\u4ecb\u8d28\u7684\u6269\u6563\u8fc7\u7a0b\u3002\u8be5\u4f20\u611f\u5668\u7ed9\u51fa\u4e86\u9002\u5f53\u6d53\u5ea6\u8303\u56f4\u5185\u7684\u7ebf\u6027\u54cd\u5e94\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u7535\u5206\u6790\u201d\u63a5\u53e3\u5c06\u5316\u5b66\u7269\u8d28\u4f20\u9012\u4e0e\u5de5\u4f5c\u7535\u6781\u548c\u5bf9\u7535\u6781\u5904\u7684\u7535\u89e3\u76f8\u8026\u5408\uff0c\u5e76\u6839\u636e\u7c73\u6c0f\u52a8\u529b\u5b66\u901a\u8fc7\u6eb6\u6db2\u4e2d\u7684\u8461\u8404\u7cd6\u6c27\u5316\u9176\u5c06\u8461\u8404\u7cd6\u6c27\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("glucose_sensor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
