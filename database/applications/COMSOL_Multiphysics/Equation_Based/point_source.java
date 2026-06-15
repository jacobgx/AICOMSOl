/*
 * point_source.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class point_source {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("lpeq", "LaplaceEquation", "geom1");
    model.component("comp1").physics("lpeq").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lpeq", true);

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("lpeq").create("dir1", "DirichletBoundary", 1);
    model.component("comp1").physics("lpeq").feature("dir1").selection().all();
    model.component("comp1").physics("lpeq").create("ptsrc1", "PointSourceTerm", 0);
    model.component("comp1").physics("lpeq").feature("ptsrc1").selection().set(3);
    model.component("comp1").physics("lpeq").feature("ptsrc1").setIndex("f", 1, 0);

    model.study("std1").feature("stat").set("errestandadap", "adaption");
    model.study("std1").feature("stat").set("meshadaptmethod", "modify");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").label("\u62c9\u666e\u62c9\u65af\u65b9\u7a0b");
    model.result("pg1").feature("surf1").set("expr", "u");
    model.result("pg1").run();
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln1").setIndex("genpoints", 0.02, 0, 0);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "cln1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").set("expr", "u+log(x^2)/(4*pi)");
    model.result("pg2").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").set("data", "dset2");
    model.result().numerical("int1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("int1").selection().set(1);
    model.result().numerical("int1").setIndex("expr", "abs(u+log(sqrt(x^2+y^2))/(2*pi))", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result("pg1").run();

    model.title("\u70b9\u6e90\u5b9e\u73b0");

    model
         .description("\u672c\u4f8b\u5728\u70b9\u6e90\u4f4d\u4e8e\u539f\u70b9\u7684\u5355\u4f4d\u5706\u76d8\u4e0a\u8ba1\u7b97\u6cca\u677e\u65b9\u7a0b\u7684\u89e3\u3002\u539f\u70b9\u9644\u8fd1\u91c7\u7528\u8f83\u9ad8\u7684\u7f51\u683c\u5bc6\u5ea6\uff0c\u4ee5\u89e3\u51b3\u70b9\u6e90\u7684\u5947\u5f02\u6027\u95ee\u9898\u3002\u6700\u540e\u5c06\u8ba1\u7b97\u5f97\u5230\u7684\u89e3\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("point_source.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
