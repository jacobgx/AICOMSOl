/*
 * superconducting_wire.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:21 by COMSOL 6.3.0.290. */
public class superconducting_wire {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Superconducting");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mfh", "MagneticFieldFormulation", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mfh", true);

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 0.1);
    model.component("comp1").geom("geom1").runPre("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("alpha", "1.449621256", "\u7535\u963b\u7387\u6a21\u578b\u53c2\u6570");
    model.param().set("Jc", "1.7e7[A/m^2]", "\u4e34\u754c\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("I0", "1e6[A]", "\u5916\u52a0\u7535\u6d41");
    model.param().set("rho_air", "1e2[ohm*m]", "\u7a7a\u6c14\u7535\u963b\u7387");
    model.param().set("tau", "0.02[s]", "\u5916\u52a0\u7535\u6d41\u7684\u65f6\u95f4\u5e38\u6570");
    model.param().set("Tc", "92[K]", "\u4e34\u754c\u6e29\u5ea6");
    model.param().set("dT", "4[K]", "\u7535\u963b\u7387\u6a21\u578b\u53c2\u6570");
    model.param().set("dJ", "Jc/1e4", "\u7535\u963b\u7387\u6a21\u578b\u53c2\u6570");
    model.param().set("E0", "0.0836168[V/m]", "\u7535\u963b\u7387\u6a21\u578b\u53c2\u6570");

    model.component("comp1").func().create("step1", "Step");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("I1", "I0*(1-exp(-t/tau))");
    model.component("comp1").variable("var1").set("H0phi", "I1/(2*pi*sqrt(x^2+y^2))");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");

    model.component("comp1").physics("mfh").prop("DivergenceConstraint").set("DivergenceConstraint", false);
    model.component("comp1").physics("mfh").feature("fl1").set("ConstitutiveRelationJcE", "ElectricalResistivity");
    model.component("comp1").physics("mfh").create("fl2", "FaradaysLaw", 2);
    model.component("comp1").physics("mfh").feature("fl2").selection().set(2);
    model.component("comp1").physics("mfh").feature("fl2").set("ConstitutiveRelationJcE", "EJCharacteristic");
    model.component("comp1").physics("mfh").create("mfb1", "MagneticFieldBoundary", 1);
    model.component("comp1").physics("mfh").feature("mfb1").selection().set(1, 2, 5, 8);
    model.component("comp1").physics("mfh").feature("mfb1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("mfh").feature("mfb1").set("H0", new String[]{"0", "H0phi", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("resistivity", new String[]{"rho_air"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u8d85\u5bfc\u4f53");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup().create("EJCurve", "EJCurve", "E-J_characteristic");
    model.component("comp1").material("mat2").propertyGroup("EJCurve")
         .set("normE", new String[]{"E0*(((normJ-Jc)/Jc)*step1((normJ-Jc)/1[A/m^2]))^alpha"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.005,0.1)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "1e-9");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "1e-3");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mfh)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
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
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "mfh.Jz");
    model.result("pg2").feature("surf1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\uff0cz \u5206\u91cf");
    model.result("pg2").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").run();

    model.title("\u8d85\u5bfc\u7ebf");

    model
         .description("\u8d85\u5bfc\u6750\u6599\u5728\u7279\u5b9a\u7684\u4e34\u754c\u7535\u6d41\u5bc6\u5ea6\u4e0b\u5177\u6709\u96f6\u7535\u963b\u7387\uff0c\u5f53\u8d85\u8fc7\u8be5\u4e34\u754c\u503c\u540e\uff0c\u7535\u963b\u7387\u4f1a\u8fc5\u901f\u589e\u5927\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u78c1\u573a\u516c\u5f0f\u201d\u7269\u7406\u573a\u4e3a\u8fd9\u79cd\u6750\u6599\u5efa\u6a21\u3002");

    model.label("superconducting_wire.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
