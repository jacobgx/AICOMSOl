/*
 * shell_diffusion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class shell_diffusion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cb", "CoefficientFormBoundaryPDE", "geom1");
    model.component("comp1").physics("cb").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("cb").field("dimensionless").component(new String[]{"V"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cb", true);

    model.param().set("sigma", "4.032e6[S/m]");
    model.param().descr("sigma", "\u7535\u5bfc\u7387");
    model.param().set("d", "1[mm]");
    model.param().descr("d", "\u58f3\u539a\u5ea6");

    model.component("comp1").geom("geom1").insertFile("shell_diffusion_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("igf1");

    model.component("comp1").physics("cb").prop("Units").set("DependentVariableQuantity", "electricpotential");
    model.component("comp1").physics("cb").prop("Units").setIndex("CustomSourceTermUnit", "A*m^-2", 0, 0);
    model.component("comp1").physics("cb").feature("cfeq1")
         .setIndex("c", new String[]{"sigma*d", "0", "0", "0", "sigma*d", "0", "0", "0", "sigma*d"}, 0);
    model.component("comp1").physics("cb").feature("cfeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("cb").create("dir1", "DirichletBoundary", 1);
    model.component("comp1").physics("cb").feature("dir1").selection().set(14, 15, 25, 29);
    model.component("comp1").physics("cb").feature("dir1").setIndex("r", 400, 0);
    model.component("comp1").physics("cb").create("dir2", "DirichletBoundary", 1);
    model.component("comp1").physics("cb").feature("dir2").selection().set(40, 41, 42, 43);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").label("\u7cfb\u6570\u5f62\u5f0f\u8fb9\u754c\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").feature("surf1").set("expr", "V");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"-sigma*VTx", "VTy", "VTz"});
    model.result("pg1").feature("arws1").setIndex("expr", "-sigma*VTy", 1);
    model.result("pg1").feature("arws1").setIndex("expr", "-sigma*VTz", 2);
    model.result("pg1").feature("arws1").set("descractive", true);
    model.result("pg1").feature("arws1").set("descr", "\u7535\u6d41\u573a (-sigma*VTx, -sigma*VTy, -sigma*VTz)");
    model.result("pg1").feature("arws1").set("arrowlength", "normalized");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "sigma*sqrt(VTx^2+VTy^2+VTz^2)");
    model.result("pg2").run();

    model.title("\u94a2\u7f50\u4e2d\u7684\u58f3\u6269\u6563");

    model
         .description("\u8fd9\u4e2a\u4e09\u7ef4\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u7f50\u58f3\u7684\u7535\u6d41\u5bc6\u5ea6\uff0c\u4ee5\u53ca\u8868\u9762\u7684\u7535\u52bf\u5206\u5e03\u3002");

    model.label("shell_diffusion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
