/*
 * bh_curve_checker.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class bh_curve_checker {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Applications");

    model.param().set("Hmax", "318310");
    model.param().descr("Hmax", "Maximum magnetic field (A/m) of the original B-H curve");
    model.param().set("Hmax2", "Hmax");
    model.param().descr("Hmax2", "Hmax after optimization, to be updated in the application");

    model.func().create("int1", "Interpolation");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0, 0, 1);
    model.func("int1").setIndex("table", 663.146, 1, 0);
    model.func("int1").setIndex("table", 1, 1, 1);
    model.func("int1").setIndex("table", 1067.5, 2, 0);
    model.func("int1").setIndex("table", 1.1, 2, 1);
    model.func("int1").setIndex("table", 1705.23, 3, 0);
    model.func("int1").setIndex("table", 1.2, 3, 1);
    model.func("int1").setIndex("table", 2463.11, 4, 0);
    model.func("int1").setIndex("table", 1.3, 4, 1);
    model.func("int1").setIndex("table", 3841.67, 5, 0);
    model.func("int1").setIndex("table", 1.4, 5, 1);
    model.func("int1").setIndex("table", 5425.74, 6, 0);
    model.func("int1").setIndex("table", 1.5, 6, 1);
    model.func("int1").setIndex("table", 7957.75, 7, 0);
    model.func("int1").setIndex("table", 1.6, 7, 1);
    model.func("int1").setIndex("table", 12298.3, 8, 0);
    model.func("int1").setIndex("table", 1.7, 8, 1);
    model.func("int1").setIndex("table", 20462.8, 9, 0);
    model.func("int1").setIndex("table", 1.8, 9, 1);
    model.func("int1").setIndex("table", 32169.6, 10, 0);
    model.func("int1").setIndex("table", 1.9, 10, 1);
    model.func("int1").setIndex("table", 61213.4, 11, 0);
    model.func("int1").setIndex("table", "2.0", 11, 1);
    model.func("int1").setIndex("table", 111408, 12, 0);
    model.func("int1").setIndex("table", 2.1, 12, 1);
    model.func("int1").setIndex("table", 175070, 13, 0);
    model.func("int1").setIndex("table", 2.2, 13, 1);
    model.func("int1").setIndex("table", 261469, 14, 0);
    model.func("int1").setIndex("table", 2.3, 14, 1);
    model.func("int1").setIndex("table", 318310, 15, 0);
    model.func("int1").setIndex("table", 2.4, 15, 1);
    model.func("int1").set("extrap", "linear");
    model.func().duplicate("int2", "int1");
    model.func("int2").setIndex("table", 2.38, 15, 1);
    model.func("int2").set("defineinv", true);
    model.func().create("int3", "Interpolation");
    model.func("int3").setIndex("table", 0, 0, 0);
    model.func("int3").setIndex("table", 0, 0, 1);
    model.func("int3").setIndex("table", 1, 1, 0);
    model.func("int3").setIndex("table", 1, 1, 1);
    model.func("int3").setIndex("table", 2, 2, 0);
    model.func("int3").setIndex("table", 2, 2, 1);
    model.func("int3").setIndex("table", "Hmax", 3, 0);
    model.func("int3").setIndex("table", 1, 3, 1);
    model.func().duplicate("int4", "int3");
    model.func("int4").setIndex("table", 4, 2, 1);
    model.func("int1").label("Original B-H curve");
    model.func("int2").label("Optimized B-H curve");
    model.func("int3").label("Differential Relative Permeability of the Original Curve");
    model.func("int3").set("funcname", "d_int1");
    model.func("int4").label("Differential Relative Permeability of the Optimized Curve");
    model.func("int4").set("funcname", "d_int2");
    model.func("int1").createPlot("pg1");

    model.result("pg1").run();

    model.func("int2").createPlot("pg2");

    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("extrapolation", "right");
    model.result("pg1").feature("plot1").set("upperbound", "Hmax");
    model.result("pg2").run();
    model.result("pg2").feature("plot1").set("data", "int2_ds1");
    model.result("pg2").feature("plot1").set("extrapolation", "right");
    model.result("pg2").feature("plot1").set("upperbound", "Hmax2");
    model.result("pg1").feature().copy("plot2", "pg2/plot1");
    model.result("pg2").feature().remove("plot1");
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg1").run();
    model.result("pg1").label("B-H Curves");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("data", "int1_ds1");
    model.result("pg1").feature("plot1").label("Original data");
    model.result("pg1").feature("plot1").set("linecolor", "custom");
    model.result("pg1").feature("plot1")
         .set("customlinecolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg1").feature("plot1").set("pointcolor", "custom");
    model.result("pg1").feature("plot1")
         .set("custompointcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg1").feature("plot1").set("extrapolationcolor", "custom");
    model.result("pg1").feature("plot1")
         .set("customextrapolationcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg1").run();
    model.result("pg1").feature("plot2").label("Optimized Data");
    model.result("pg1").feature("plot2").set("linecolor", "red");
    model.result("pg1").feature("plot2").set("pointcolor", "red");

    model.func("int3").createPlot("pg2");

    model.result("pg2").run();

    model.func("int4").createPlot("pg3");

    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").label("Differential Relative Permeability");
    model.result("pg2").run();
    model.result("pg2").feature("plot1").label("Original Data");
    model.result("pg2").feature("plot1").set("upperbound", "Hmax");
    model.result("pg2").feature("plot1").set("linecolor", "custom");
    model.result("pg2").feature("plot1")
         .set("customlinecolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg2").feature("plot1").set("pointcolor", "custom");
    model.result("pg2").feature("plot1")
         .set("custompointcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg2").feature("plot1").set("extrapolationcolor", "custom");
    model.result("pg2").feature("plot1")
         .set("customextrapolationcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg3").run();
    model.result("pg3").feature("plot1").label("Optimized Data");
    model.result("pg3").feature("plot1").set("data", "int4_ds1");
    model.result("pg3").feature("plot1").set("upperbound", "Hmax2");
    model.result("pg3").feature("plot1").set("linecolor", "red");
    model.result("pg3").feature("plot1").set("pointcolor", "red");
    model.result("pg2").feature().copy("plot2", "pg3/plot1");
    model.result("pg3").feature().remove("plot1");
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().dataset("int1_ds1").set("parmin1", 0);
    model.result().dataset("int1_ds1").set("parmax1", "Hmax*1.2");
    model.result().dataset("int2_ds1").set("parmin1", 0);
    model.result().dataset("int2_ds1").set("parmax1", "Hmax2*1.2");
    model.result().dataset("int3_ds1").set("parmin1", 0);
    model.result().dataset("int3_ds1").set("parmax1", "Hmax*1.2");
    model.result().dataset("int4_ds1").set("parmin1", 0);
    model.result().dataset("int4_ds1").set("parmax1", "Hmax2*1.2");
    model.result().dataset().duplicate("ptds5", "ptds1");
    model.result().dataset("ptds5").label("Interpolation for Smooth");
    model.result().dataset("ptds5").set("pointx", "10^{range(log10(1),1/20,log10(Hmax))} Hmax");
    model.result().dataset().duplicate("int1_ds2", "int1_ds1");
    model.result().dataset("int1_ds2").label("Grid 1D Used for Extrapolation");
    model.result().dataset("int1_ds2").set("parmax1", "Hmax2*1.2");
    model.result().dataset().duplicate("ptds6", "ptds5");
    model.result().dataset("ptds6").label("Interpolation for Extrapolation");
    model.result().dataset("ptds6").set("data", "int1_ds2");
    model.result().dataset("ptds6").set("pointx", "10^{range(log10(Hmax2/10),1/20,log10(Hmax2))}");
    model.result().dataset().duplicate("ptds7", "ptds5");
    model.result().dataset("ptds7").label("Inverse Interpolation for Well-Defined BH Values");
    model.result().dataset("ptds7").set("data", "int2_ds1");
    model.result().dataset("ptds7").set("pointx", "2.1 2.2 2.3 2.4");
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").set("data", "ptds5");
    model.result().numerical("pev1").setIndex("expr", "t", 0);
    model.result().numerical("pev1").setIndex("unit", "s", 0);
    model.result().numerical("pev1").setIndex("descr", "", 0);
    model.result().numerical().create("pev2", "EvalPoint");
    model.result().numerical("pev2").set("data", "ptds5");
    model.result().numerical("pev2").setIndex("expr", "int1(t)", 0);
    model.result().numerical().create("pev3", "EvalPoint");
    model.result().numerical("pev3").set("data", "ptds6");
    model.result().numerical("pev3").setIndex("expr", "t", 0);
    model.result().numerical("pev3").setIndex("unit", "s", 0);
    model.result().numerical("pev3").setIndex("descr", "", 0);
    model.result().numerical().create("pev4", "EvalPoint");
    model.result().numerical("pev4").set("data", "ptds7");
    model.result().numerical("pev4").setIndex("expr", "int2_inv(t)", 0);
    model.result().numerical("pev4").setIndex("descr", "Inverse interpolation", 0);
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "Magnetic field <b>H</b>, A/m");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "Magnetic flux density <b>B</b>, T");
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Magnetic field <b>H</b>, A/m");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2")
         .set("ylabel", "Differential relative permeability \\partial <b>B</b>/\\partial <b>H</b>/\\mu<sub>0</sub>");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");

    model.title(null);

    model.description("");

    model.label("bh_curve_checker_embedded.mph");

    model.result("pg1").run();

    model.title("B-H \u66f2\u7ebf\u68c0\u67e5\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4ece\u6587\u672c\u6587\u4ef6\u5bfc\u5165\u6d4b\u91cf\u6570\u636e\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u5904\u7406\u6d4b\u91cf\u7684\u6570\u636e\n\u2022 \u5c06\u7ed3\u679c\u5bfc\u51fa\u81f3\u6587\u672c\u6587\u4ef6\u3002\n\n\u8be5 App \u53ef\u7528\u4e8e\u4f7f\u7528\u5b9e\u9a8c\u6570\u636e\u6765\u9a8c\u8bc1\u548c\u4f18\u5316 B-H \u66f2\u7ebf\u3002\u4e0d\u4ec5\u53ef\u4ee5\u5728\u96be\u4ee5\u8fdb\u884c\u6d4b\u91cf\u7684\u8d85\u901a\u91cf\u533a\u57df\u4e2d\u751f\u6210\u66f2\u7ebf\u6570\u636e\uff0c\u8fd8\u53ef\u4ee5\u6d88\u9664\u53ef\u80fd\u5bfc\u81f4\u6570\u503c\u4e0d\u7a33\u5b9a\u7684 B-H \u66f2\u7ebf\u659c\u7387\u7684\u975e\u7269\u7406\u6ce2\u52a8\u3002\n\n\u539f\u59cb B-H \u66f2\u7ebf\u53ef\u4ee5\u4ece\u4e24\u4e2a\u65b9\u9762\u8fdb\u884c\u8ba1\u7b97\u3002\u9996\u5148\uff0c\u4ece\u7269\u7406\u89d2\u5ea6\u9a8c\u8bc1\u66f2\u7ebf\u7684\u5916\u63a8\u662f\u5426\u5408\u7406\u3002\u5176\u6b21\uff0c\u68c0\u67e5\u66f2\u7ebf\u7684\u659c\u7387\u662f\u5426\u5e73\u6ed1\u3002\u4f18\u5316\u7b97\u6cd5\u4e3b\u8981\u5206\u522b\u57fa\u4e8e\u6307\u6570\u5916\u63a8\u6cd5\u548c\u7ebf\u6027\u63d2\u503c\u6cd5\u3002\n\n\u8be5 App \u9700\u8981\u5728\u6587\u672c\u8f93\u5165\u6587\u4ef6\u4e2d\u5b9a\u4e49\u7684\u539f\u59cb\u66f2\u7ebf\u6570\u636e\u3002\u5bfc\u5165\u66f2\u7ebf\u540e\uff0cApp \u4f1a\u68c0\u67e5\u662f\u5426\u9700\u8981\u4f18\u5316\u3002\u901a\u8fc7\u5355\u51fb\u201c\u4f18\u5316\u66f2\u7ebf\u201d\u6309\u94ae\uff0c\u7528\u6237\u53ef\u4ee5\u751f\u6210\u4f18\u5316\u7684\u66f2\u7ebf\u6570\u636e\uff0c\u8fd9\u4e9b\u6570\u636e\u53ef\u4ee5\u5bfc\u51fa\u81f3\u6587\u672c\u6587\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.label("bh_curve_checker.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
