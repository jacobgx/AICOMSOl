/*
 * black_scholes_put.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class black_scholes_put {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");
    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/c", true);

    model.baseSystem("none");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 80, 1);
    model.component("comp1").geom("geom1").run("i1");

    model.param().set("r", "0.12");
    model.param().descr("r", "\u8fde\u7eed\u590d\u5408\u5229\u7387");
    model.param().set("sigma", "0.3");
    model.param().descr("sigma", "\u4e0d\u7a33\u5b9a\u6027");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", "1/2*sigma^2*x^2", 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("a", "r", 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("da", -1, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("be", "(-r+sigma^2)*x", 0);
    model.component("comp1").physics("c").feature("init1").set("u", "(x<40)*(40-x)");
    model.component("comp1").physics("c").create("flux1", "FluxBoundary", 0);
    model.component("comp1").physics("c").feature("flux1").selection().set(1);
    model.component("comp1").physics("c").create("dir1", "DirichletBoundary", 0);
    model.component("comp1").physics("c").feature("dir1").selection().set(2);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(12,-0.5,0)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "u");
    model.result("pg1").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{25}, 0);
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "x");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "u");
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendprefix", "\u65f6\u95f4 = ");
    model.result("pg1").run();

    model.title("Black-Scholes \u65b9\u7a0b");

    model
         .description("\u672c\u4f8b\u6c42\u89e3\u6b27\u5f0f\u770b\u8dcc\u671f\u6743\u4ef7\u503c\u7684 Black-Scholes \u65b9\u7a0b\uff0c\u6b64\u65b9\u7a0b\u7531 Black \u548c Scholes \u9996\u5148\u63d0\u51fa\uff0c\u5e76\u7ed9\u51fa\u4e86\u4e00\u4e2a\u89e3\u6790\u89e3\u7684\u8868\u8fbe\u5f0f\u3002");

    model.label("black_scholes_put.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
