/*
 * transmission_line_lpf.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:37 by COMSOL 6.3.0.290. */
public class transmission_line_lpf {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Filters");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tl", "TransmissionLine", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/tl", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "4[GHz]", "\u9891\u7387");
    model.param().set("lda0", "c_const/f0", "\u6ce2\u957f\uff0c\u81ea\u7531\u7a7a\u95f4");
    model.param().set("L0", "250[nH/m]", "\u5fae\u5e26\u7ebf\u7535\u611f");
    model.param().set("C0", "100[pF/m]", "\u5fae\u5e26\u7ebf\u7535\u5bb9");
    model.param().set("Z0", "sqrt(L0/C0)", "\u7279\u6027\u963b\u6297");
    model.param().set("lda0_t", "1/(f0*sqrt(L0*C0))", "\u6ce2\u957f\uff0c\u4f20\u8f93\u7ebf");
    model.param().set("ul", "lda0_t/8", "\u5355\u4f4d\u957f\u5ea6");
    model.param().set("z0", "Z0/50[ohm]", "\u5f52\u4e00\u5316\u963b\u6297");
    model.param().set("g1", "1.5963", "\u5143\u4ef6\u503c 1\uff0c0.5dB \u7b49\u6ce2\u7eb9");
    model.param().set("g2", "1.0967", "\u5143\u4ef6\u503c 2\uff0c0.5dB \u7b49\u6ce2\u7eb9");
    model.param().set("n_sq", "1+1/g1", "Kuroda \u7b49\u5f0f\u4e2d\u7684 n^2 \u53c2\u6570");
    model.param().set("z1_1", "g1*n_sq", "\u5f52\u4e00\u5316\u8fc7\u6e21\u963b\u6297");
    model.param()
         .set("z1_2", "z0*n_sq", "\u5f00\u8def\u5e76\u8054\u77ed\u622a\u7ebf 1 \u5f52\u4e00\u5316\u963b\u6297");
    model.param().set("z2", "1/g2", "\u5f00\u8def\u5e76\u8054\u77ed\u622a\u7ebf 2 \u5f52\u4e00\u5316\u963b\u6297");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-ul-0.5", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"ul+0.5", "0"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"-ul", "0"});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"-ul", "ul"});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"0", "ul"});
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("ls4", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls4").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls4").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls4").set("coord1", new String[]{"ul", "0"});
    model.component("comp1").geom("geom1").feature("ls4").set("coord2", new String[]{"ul", "ul"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("tl").create("lport1", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport1").selection().set(1);
    model.component("comp1").physics("tl").feature("lport1").set("PortExcitation", "on");
    model.component("comp1").physics("tl").create("lport2", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport2").selection().set(8);
    model.component("comp1").physics("tl").feature("tle1").set("L", "L0");
    model.component("comp1").physics("tl").feature("tle1").set("C", "C0");
    model.component("comp1").physics("tl").create("tle2", "TransmissionLineEquation", 1);
    model.component("comp1").physics("tl").feature("tle2").selection().set(3, 5);
    model.component("comp1").physics("tl").feature("tle2").set("L", "L0*z1_1");
    model.component("comp1").physics("tl").feature("tle2").set("C", "C0/z1_1");
    model.component("comp1").physics("tl").create("tle3", "TransmissionLineEquation", 1);
    model.component("comp1").physics("tl").feature("tle3").selection().set(2, 6);
    model.component("comp1").physics("tl").feature("tle3").set("L", "L0*z1_2");
    model.component("comp1").physics("tl").feature("tle3").set("C", "C0/z1_2");
    model.component("comp1").physics("tl").create("tle4", "TransmissionLineEquation", 1);
    model.component("comp1").physics("tl").feature("tle4").selection().set(4);
    model.component("comp1").physics("tl").feature("tle4").set("L", "L0*z2");
    model.component("comp1").physics("tl").feature("tle4").set("C", "C0/z2");

    model.study("std1").feature("freq").set("plist", "range(1[GHz],0.1[GHz],20[GHz])");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("\u7ebf\u7ed3\u679c\u56fe");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"tl.S11dB", "tl.S21dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg2").label("S \u53c2\u6570 (tl)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("xdataunit", "GHz");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "SmithGroup");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rgr1", "ReflectionGraph");
    model.result("pg3").feature("rgr1").set("unit", new String[]{""});
    model.result("pg3").feature("rgr1").set("expr", new String[]{"tl.S11"});
    model.result("pg3").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg3").label("\u53f2\u5bc6\u65af\u56fe (tl)");
    model.result("pg3").feature("rgr1").set("titletype", "manual");
    model.result("pg3").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg3").feature("rgr1").set("linemarker", "point");
    model.result("pg3").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("rgr1").create("col1", "Color");
    model.result("pg3").feature("rgr1").feature("col1").set("expr", "tl.freq/1e9");
    model.result("pg3").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 26, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "0.5 dB \u7b49\u6ce2\u7eb9\u4f4e\u901a\u6ee4\u6ce2\u5668\uff0c\u622a\u6b62\u9891\u7387\u4e3a 4GHz");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("ymin", -50);
    model.result("pg2").set("legendpos", "lowerright");

    model.title("\u4f20\u8f93\u7ebf\u4f4e\u901a\u6ee4\u6ce2\u5668\u7684\u5feb\u901f\u5efa\u6a21");

    model
         .description("\u8bbe\u8ba1\u6ee4\u6ce2\u5668\u7684\u4e00\u79cd\u65b9\u6cd5\u662f\u4f7f\u7528\u4f17\u6240\u5468\u77e5\u7684\u6ee4\u6ce2\u5668\u539f\u578b\u7684\u5143\u4ef6\u503c\uff0c\u4f8b\u5982\u6700\u5927\u5e73\u5766\u4f4e\u901a\u6ee4\u6ce2\u5668\u6216\u7b49\u6ce2\u7eb9\u4f4e\u901a\u6ee4\u6ce2\u5668\u3002\u4e0e\u96c6\u603b\u5143\u4ef6\u6ee4\u6ce2\u5668\u76f8\u6bd4\uff0c\u5728\u5fae\u6ce2\u57fa\u677f\u4e0a\u5236\u9020\u5206\u5e03\u5143\u4ef6\u6ee4\u6ce2\u5668\u66f4\u52a0\u5bb9\u6613\uff0c\u8fd9\u662f\u56e0\u4e3a\u5f88\u96be\u627e\u5230\u4e0e\u6ee4\u6ce2\u5668\u539f\u578b\u7684\u9891\u7387\u6807\u5ea6\u5143\u4ef6\u503c\u5b8c\u5168\u5339\u914d\u7684\u73b0\u6210\u7535\u5bb9\u5668\u548c\u7535\u611f\u5668\u3002\n\n\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u4e86\u4f7f\u7528\u7406\u67e5\u5fb7\u53d8\u6362\u3001Kuroda \u7b49\u5f0f\u4ee5\u53ca\u4f20\u8f93\u7ebf \u63a5\u53e3\u6765\u8bbe\u8ba1\u5206\u5e03\u5143\u4ef6\u6ee4\u6ce2\u5668\u7684\u8fc7\u7a0b\u3002\u4e0e\u6c42\u89e3\u4e09\u7ef4\u9ea6\u514b\u65af\u97e6\u65b9\u7a0b\u7ec4\u76f8\u6bd4\uff0c\u8fd9\u79cd\u65b9\u6cd5\u901f\u5ea6\u975e\u5e38\u5feb\u3002\u8be5\u6a21\u578b\u6a21\u62df\u4e00\u4e2a\u4e09\u5143\u4ef6 0.5 dB \u7b49\u6ce2\u7eb9\u4f4e\u901a\u6ee4\u6ce2\u5668\uff0c\u5176\u622a\u6b62\u9891\u7387\u4e3a 4\u00a0GHz\u3002\u751f\u6210\u7684 S \u53c2\u6570\u56fe\u663e\u793a\u4e86\u4f4e\u901a\u9891\u7387\u54cd\u5e94\uff0c\u5728\u8f83\u9ad8\u9891\u7387\u8303\u56f4\u5185\u4e5f\u80fd\u5468\u671f\u6027\u5730\u89c2\u5bdf\u5230\u8be5\u54cd\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("transmission_line_lpf.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
