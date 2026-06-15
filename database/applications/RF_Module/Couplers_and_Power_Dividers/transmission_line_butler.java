/*
 * transmission_line_butler.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:31 by COMSOL 6.3.0.290. */
public class transmission_line_butler {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Couplers_and_Power_Dividers");

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
    model.param().set("PortName", "1", "\u7528\u4e8e\u626b\u63cf\u7684\u7aef\u53e3\u540d\u79f0\u53c2\u6570");
    model.param().set("f0", "30[GHz]", "\u9891\u7387");
    model.param().set("lda0", "c_const/f0", "\u6ce2\u957f\uff0c\u81ea\u7531\u7a7a\u95f4");
    model.param().set("L0", "250[nH/m]", "\u5fae\u5e26\u7ebf\u7535\u611f");
    model.param().set("C0", "100[pF/m]", "\u5fae\u5e26\u7ebf\u7535\u5bb9");
    model.param().set("Z0", "sqrt(L0/C0)", "\u7279\u6027\u963b\u6297");
    model.param().set("lda0_t", "1/(f0*sqrt(L0*C0))", "\u6ce2\u957f\uff0c\u4f20\u8f93\u7ebf");
    model.param().set("ul", "lda0_t/4", "\u5355\u4f4d\u957f\u5ea6\uff0c\u56db\u5206\u4e4b\u4e00\u6ce2\u957f");
    model.param().set("Z1", "Z0/sqrt(2)", "\u5206\u652f\u7ebf\u7684\u7279\u6027\u963b\u6297");
    model.param().set("z1", "Z1/Z0", "\u5206\u652f\u7ebf\u7684\u5f52\u4e00\u5316\u963b\u6297");
    model.param().set("array_d", "0.48*lda0", "\u9635\u5217\u5355\u5143\u95f4\u8ddd");

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("90 \u5ea6\u6df7\u5408\u8026\u5408\u5668");
    model.geom("part1").lengthUnit("mm");
    model.geom("part1").create("ls1", "LineSegment");
    model.geom("part1").feature("ls1").set("specify1", "coord");
    model.geom("part1").feature("ls1").set("specify2", "coord");
    model.geom("part1").feature("ls1").set("coord2", new String[]{"ul*2", "0"});
    model.geom("part1").run("ls1");
    model.geom("part1").create("ls2", "LineSegment");
    model.geom("part1").feature("ls2").set("specify1", "coord");
    model.geom("part1").feature("ls2").set("specify2", "coord");
    model.geom("part1").feature("ls2").set("coord1", new String[]{"ul/2", "0"});
    model.geom("part1").feature("ls2").set("coord2", new String[]{"ul/2", "ul"});
    model.geom("part1").run("ls2");
    model.geom("part1").create("rot1", "Rotate");
    model.geom("part1").feature("rot1").selection("input").set("ls1", "ls2");
    model.geom("part1").feature("rot1").set("rot", "0 180");
    model.geom("part1").feature("rot1").set("pos", new String[]{"ul", "ul/2"});
    model.geom("part1").run("rot1");
    model.geom().create("part2", "Part", 2);
    model.geom("part2").label("45 \u5ea6\u5ef6\u8fdf\u7ebf");
    model.geom("part2").lengthUnit("mm");
    model.geom("part2").create("pol1", "Polygon");
    model.geom("part2").feature("pol1").set("source", "table");
    model.geom("part2").feature("pol1").set("type", "open");
    model.geom("part2").feature("pol1").set("source", "vectors");
    model.geom("part2").feature("pol1").set("x", "0 ul ul ul ul ul*2 ul*2 ul*2 ul*2 ul*3");
    model.geom("part2").feature("pol1").set("y", "0 0 0 ul*0.75 ul*0.75 ul*0.75 ul*0.75 0 0 0");
    model.geom("part2").run("pol1");
    model.geom().create("part3", "Part", 2);
    model.geom("part3").label("\u79fb\u76f8\u5668");
    model.geom("part3").lengthUnit("mm");
    model.geom("part3").create("pol1", "Polygon");
    model.geom("part3").feature("pol1").set("source", "table");
    model.geom("part3").feature("pol1").set("type", "open");
    model.geom("part3").feature("pol1").set("source", "vectors");
    model.geom("part3").feature("pol1").set("x", "0 ul ul ul ul ul*2 ul*2 ul*2 ul*2 ul*3");
    model.geom("part3").feature("pol1").set("y", "0 0 0 ul/2 ul/2 ul/2 ul/2 0 0 0");
    model.geom("part3").run("pol1");
    model.geom().create("part4", "Part", 2);
    model.geom("part4").label("\u4ea4\u53c9\u8026\u5408\u5668");
    model.geom("part4").lengthUnit("mm");
    model.geom("part4").create("ls1", "LineSegment");
    model.geom("part4").feature("ls1").set("specify1", "coord");
    model.geom("part4").feature("ls1").set("specify2", "coord");
    model.geom("part4").feature("ls1").set("coord2", new String[]{"ul*3", "0"});
    model.geom("part4").run("ls1");
    model.geom("part4").create("ls2", "LineSegment");
    model.geom("part4").feature("ls2").set("specify1", "coord");
    model.geom("part4").feature("ls2").set("specify2", "coord");
    model.geom("part4").feature("ls2").set("coord1", new String[]{"0", "ul"});
    model.geom("part4").feature("ls2").set("coord2", new String[]{"ul*3", "ul"});
    model.geom("part4").run("ls2");
    model.geom("part4").create("ls3", "LineSegment");
    model.geom("part4").feature("ls3").set("specify1", "coord");
    model.geom("part4").feature("ls3").set("specify2", "coord");
    model.geom("part4").feature("ls3").set("coord1", new String[]{"ul/2", "0"});
    model.geom("part4").feature("ls3").set("coord2", new String[]{"ul/2", "ul"});
    model.geom("part4").run("ls3");
    model.geom("part4").create("arr1", "Array");
    model.geom("part4").feature("arr1").selection("input").set("ls3");
    model.geom("part4").feature("arr1").set("fullsize", new int[]{3, 1});
    model.geom("part4").feature("arr1").set("displ", new String[]{"ul", "0"});
    model.geom("part4").run("arr1");
    model.geom().create("part5", "Part", 2);
    model.geom("part5").label("\u524d\u7aef\uff0c\u5916\u90e8");
    model.geom("part5").lengthUnit("mm");
    model.geom("part5").create("pol1", "Polygon");
    model.geom("part5").feature("pol1").set("source", "table");
    model.geom("part5").feature("pol1").set("type", "open");
    model.geom("part5").feature("pol1").set("source", "vectors");
    model.geom("part5").feature("pol1").set("x", "0 0 0 -1.5*array_d+ul*6");
    model.geom("part5").feature("pol1").set("y", "0 -1.5*(array_d-ul) -1.5*(array_d-ul) -1.5*(array_d-ul)");
    model.geom("part5").run("pol1");
    model.geom().create("part6", "Part", 2);
    model.geom("part6").label("\u524d\u7aef\uff0c\u5185\u90e8");
    model.geom("part6").lengthUnit("mm");
    model.geom("part6").create("pol1", "Polygon");
    model.geom("part6").feature("pol1").set("source", "table");
    model.geom("part6").feature("pol1").set("type", "open");
    model.geom("part6").feature("pol1").set("source", "vectors");
    model.geom("part6").feature("pol1")
         .set("x", "0 ul*0.25 ul*0.25 ul*0.25 ul*0.25 ul*0.5 ul*0.5 ul*0.5 ul*0.5 ul*0.75 ul*0.75 ul*0.75 ul*0.75 -1.5*array_d+ul*6");
    model.geom("part6").feature("pol1")
         .set("y", "0 0 0 -(array_d-ul)/2 -(array_d-ul)/2 -(array_d-ul)/2 -(array_d-ul)/2 0 0 0 0 -(array_d-ul)/2 -(array_d-ul)/2 -(array_d-ul)/2");
    model.geom("part6").run("pol1");
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"ul*5", "0"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"ul*2", "0"});
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi4").set("displ", new String[]{"ul*7", "0"});
    model.component("comp1").geom("geom1").run("pi4");
    model.component("comp1").geom("geom1").create("pi5", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi5").set("part", "part5");
    model.component("comp1").geom("geom1").feature("pi5").set("displ", new String[]{"ul*10", "0"});
    model.component("comp1").geom("geom1").run("pi5");
    model.component("comp1").geom("geom1").create("pi6", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi6").set("part", "part6");
    model.component("comp1").geom("geom1").feature("pi6").set("displ", new String[]{"ul*10", "ul"});
    model.component("comp1").geom("geom1").run("pi6");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input")
         .set("pi1", "pi2", "pi3", "pi4", "pi5", "pi6");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"0", "ul*1.5"});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("pi7", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi7").set("part", "part4");
    model.component("comp1").geom("geom1").feature("pi7").set("displ", new String[]{"ul*2", "ul"});
    model.component("comp1").geom("geom1").run("pi7");
    model.component("comp1").geom("geom1").create("pi8", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi8").set("part", "part4");
    model.component("comp1").geom("geom1").feature("pi8").set("displ", new String[]{"ul*7", "ul"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tl").feature("tle1").set("L", "L0");
    model.component("comp1").physics("tl").feature("tle1").set("C", "C0");
    model.component("comp1").physics("tl").create("tle2", "TransmissionLineEquation", 1);
    model.component("comp1").physics("tl").feature("tle2").selection().set(6, 7, 9, 10, 43, 44, 46, 47);
    model.component("comp1").physics("tl").feature("tle2").set("L", "L0*z1");
    model.component("comp1").physics("tl").feature("tle2").set("C", "C0/z1");
    model.component("comp1").physics("tl").create("lport1", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport1").selection().set(4);
    model.component("comp1").physics("tl").create("lport2", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport2").selection().set(3);
    model.component("comp1").physics("tl").create("lport3", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport3").selection().set(2);
    model.component("comp1").physics("tl").create("lport4", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport4").selection().set(1);
    model.component("comp1").physics("tl").create("lport5", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport5").selection().set(82);
    model.component("comp1").physics("tl").create("lport6", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport6").selection().set(81);
    model.component("comp1").physics("tl").create("lport7", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport7").selection().set(80);
    model.component("comp1").physics("tl").create("lport8", "LumpedPort", 0);
    model.component("comp1").physics("tl").feature("lport8").selection().set(79);
    model.component("comp1").physics("tl").prop("PortSweepSettings").set("useSweep", true);

    model.param("default").setShowInParamSel(true);

    model.study("std1").create("param1", "Parametric");
    model.study("std1").feature("param1").set("pname", "PortName");
    model.study("std1").feature("param1").set("plistarr", "1 2 3 4 5 6 7 8");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "ul/15");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").feature("param1").setIndex("plistarr", "1 2 3 4", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "20*log10(abs(V))");
    model.result("pg1").run();
    model.result("pg1").feature("line1").set("rangecoloractive", true);
    model.result("pg1").feature("line1").set("rangecolormin", -10);
    model.result("pg1").feature("line1").set("rangecolormax", 0);
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "arg(tl.Vport_5)", 0);
    model.result().numerical("gev1").setIndex("unit", "deg", 0);
    model.result().numerical("gev1").setIndex("descr", "\u7aef\u53e3 5 \u76f8\u4f4d", 0);
    model.result().numerical("gev1").setIndex("expr", "arg(tl.Vport_6)", 1);
    model.result().numerical("gev1").setIndex("unit", "deg", 1);
    model.result().numerical("gev1").setIndex("descr", "\u7aef\u53e3 6 \u76f8\u4f4d", 1);
    model.result().numerical("gev1").setIndex("expr", "arg(tl.Vport_7)", 2);
    model.result().numerical("gev1").setIndex("unit", "deg", 2);
    model.result().numerical("gev1").setIndex("descr", "\u7aef\u53e3 7 \u76f8\u4f4d", 2);
    model.result().numerical("gev1").setIndex("expr", "arg(tl.Vport_8)", 3);
    model.result().numerical("gev1").setIndex("unit", "deg", 3);
    model.result().numerical("gev1").setIndex("descr", "\u7aef\u53e3 8 \u76f8\u4f4d", 3);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model
         .title("\u5df4\u7279\u52d2\u77e9\u9635\u6ce2\u675f\u5f62\u6210\u7f51\u7edc\u7684\u5feb\u901f\u539f\u578b\u8bbe\u8ba1");

    model
         .description("\u5df4\u7279\u52d2\u77e9\u9635\u662f\u4e00\u79cd\u65e0\u6e90\u6ce2\u675f\u5f62\u6210\u9988\u7535\u7f51\u7edc\u3002\u7531\u4e8e\u7535\u8def\u53ef\u4ee5\u91c7\u7528\u5fae\u5e26\u7ebf\u7684\u5f62\u5f0f\u5236\u9020\uff0c\u5e76\u4e14\u662f\u65e0\u9700\u90e8\u7f72\u6602\u8d35\u7684\u6709\u6e90\u5668\u4ef6\u5373\u53ef\u6267\u884c\u6ce2\u675f\u626b\u63cf\u7684\u53ef\u884c\u89e3\u51b3\u65b9\u6848\uff0c\u56e0\u6b64\u5b83\u662f\u4e00\u79cd\u7528\u4e8e\u76f8\u63a7\u9635\u5929\u7ebf\u7684\u7ecf\u6d4e\u9ad8\u6548\u7684\u9988\u7535\u7f51\u7edc\u3002\n\n\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u4f20\u8f93\u7ebf \u63a5\u53e3\u6765\u8bbe\u8ba1\u6b64\u7c7b\u7535\u8def\u3002\u7ed3\u679c\u663e\u793a\u4e86\u5df4\u7279\u52d2\u77e9\u9635\u6ce2\u675f\u5f62\u6210\u7535\u8def\u5728 30\u00a0GHz \u4e0b\u7684\u5bf9\u6570\u7535\u538b\uff0c\u4ee5\u53ca\u6bcf\u4e2a\u8f93\u51fa\u7aef\u53e3\u7684\u7b97\u672f\u76f8\u4f4d\u7ea7\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("transmission_line_butler.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
