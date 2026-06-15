/*
 * electrochemical_polishing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class electrochemical_polishing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Electromagnetics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ec", true);

    model.param().set("K", "1e-11[m^3/(A*s)]");
    model.param().descr("K", "\u6bd4\u4f8b\u7cfb\u6570");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{2.8, 0.4});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{-1.4, 0});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.3);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{0, 0.6});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("dif1");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("dx", "x-Xg");
    model.component("comp1").variable("var1").descr("dx", "x \u4f4d\u79fb");
    model.component("comp1").variable("var1").set("dy", "y-Yg");
    model.component("comp1").variable("var1").descr("dy", "y \u4f4d\u79fb");

    model.component("comp1").physics("ec").prop("EquationForm").setIndex("form", "Stationary", 0);
    model.component("comp1").physics("ec").feature("cucn1").set("sigma_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn1").set("sigma", new int[]{10, 0, 0, 0, 10, 0, 0, 0, 10});
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot1").selection().set(3, 4, 6, 7);
    model.component("comp1").physics("ec").feature("pot1").set("V0", 30);
    model.component("comp1").physics("ec").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(2);

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").set("smoothingType", "laplace");
    model.component("comp1").common().create("pnmv1", "PrescribedNormalMeshVelocityDeformedGeometry");
    model.component("comp1").common("pnmv1").selection().set(3, 4, 6, 7);
    model.component("comp1").common("pnmv1").set("prescribedNormalVelocity", "-K*(-ec.nJ)");
    model.component("comp1").common().create("pnmd1", "PrescribedNormalMeshDisplacementDeformedGeometry");
    model.component("comp1").common("pnmd1").selection().set(1, 2, 5);

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,10)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "ec.normE");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg3").create("mesh1", "Mesh");
    model.result("pg3").feature("mesh1").set("meshdomain", "surface");
    model.result("pg3").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg3").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg3").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg3").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg3").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg3").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg3").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "ec.normJ");
    model.result("pg4").feature("surf1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg4").run();
    model.result("pg4").create("mmp1", "MaxMinPoint");
    model.result("pg4").feature("mmp1").set("expr", "ec.normJ");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u8017\u5c3d\uff0cy \u65b9\u5411");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "dy");
    model.result("pg5").feature("surf1").set("descr", "y \u4f4d\u79fb");
    model.result("pg5").run();
    model.result("pg5").create("mmp1", "MaxMinPoint");
    model.result("pg5").feature("mmp1").set("expr", "dy");
    model.result("pg5").feature("mmp1").set("display", "max");
    model.result("pg5").run();

    model.title("\u7535\u5316\u5b66\u629b\u5149");

    model
         .description("\u672c\u4f8b\u89e3\u91ca\u4e86\u7535\u5316\u5b66\u629b\u5149\u7684\u539f\u7406\u3002\u4e24\u4e2a\u7535\u6781\u548c\u4e2d\u95f4\u7684\u7535\u89e3\u8d28\u57df\u6784\u6210\u4e86\u7b80\u5316\u7684\u4e8c\u7ef4\u6a21\u578b\u51e0\u4f55\uff0c\u5176\u4e2d\u6b63\u6781\u4e0a\u6709\u4e00\u4e2a\u7a81\u8d77\uff0c\u8868\u793a\u8868\u9762\u5b58\u5728\u7f3a\u9677\u3002\u672c\u4f8b\u8bf4\u660e\u4e86\u7a81\u8d77\u548c\u5468\u56f4\u7684\u7535\u6781\u6750\u6599\u5982\u4f55\u5728\u4e00\u6bb5\u65f6\u95f4\u5185\u8017\u5c3d\u3002\u201c\u53d8\u5f62\u51e0\u4f55\u201d\u63a5\u53e3\u663e\u793a\u4e86\u7535\u6781\u8868\u9762\u7684\u51e0\u4f55\u53d8\u5316\u3002");

    model.label("electrochemical_polishing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
