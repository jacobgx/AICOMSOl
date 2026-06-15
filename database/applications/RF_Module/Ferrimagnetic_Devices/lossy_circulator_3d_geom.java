/*
 * lossy_circulator_3d_geom.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:35 by COMSOL 6.3.0.290. */
public class lossy_circulator_3d_geom {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Ferrimagnetic_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);

    model.param().set("sc_chamfer", "3");
    model.param().descr("sc_chamfer", "\u51e0\u4f55\u6bd4\u4f8b\u56e0\u5b50");
    model.param().set("sc_ferrite", "0.5");
    model.param().descr("sc_ferrite", "\u51e0\u4f55\u6bd4\u4f8b\u56e0\u5b50");

    model.variable().create("var1");

//    To import content from file, use:
//    model.variable("var1").loadFile("FILENAME");
    model.variable("var1")
         .set("eps0", "8.854187817e-12[F/m]", "\u81ea\u7531\u7a7a\u95f4\u7684\u4ecb\u7535\u5e38\u6570");
    model.variable("var1").set("mu0", "4e-7*pi[H/m]", "\u81ea\u7531\u7a7a\u95f4\u78c1\u5bfc\u7387");
    model.variable("var1").set("w", "2*pi*freq", "\u89d2\u9891\u7387");
    model.variable("var1").set("gamma", "1.759e11[C/kg]", "\u65cb\u78c1\u6bd4");
    model.variable("var1").set("H0", "(100*1e3/(4*pi))[A/m]", "\u5916\u52a0\u504f\u7f6e\u78c1\u573a");
    model.variable("var1").set("w0", "mu0*gamma*H0", "\u62c9\u83ab\u5c14\u9891\u7387");
    model.variable("var1").set("Ms", "680e-4[Wb/m^2]/mu0", "\u9971\u548c\u78c1\u5316\u5f3a\u5ea6");
    model.variable("var1")
         .set("wm", "mu0*gamma*Ms", "\u9971\u548c\u6781\u9650\u4e0b\u7684\u62c9\u83ab\u5c14\u9891\u7387");
    model.variable("var1").set("dH", "(40*1e3/(4*pi))[A/m]", "\u7ebf\u5bbd");
    model.variable("var1").set("alpha", "dH*mu0*gamma/(2*w)", "\u963b\u5c3c\u7cfb\u6570");
    model.variable("var1")
         .set("chim_xx_p", "(w0*wm*(w0^2-w^2)+w0*wm*w^2*alpha^2)/((w0^2-w^2*(1+alpha^2))^2+4*(w0*w*alpha)^2)", "\u78c1\u5316\u7387\uff0c\u5b9e\u90e8");
    model.variable("var1")
         .set("chim_xx_b", "(alpha*w*wm*(w0^2+w^2*(1+alpha^2)))/((w0^2-w^2*(1+alpha^2))^2+4*(w0*w*alpha)^2)", "\u78c1\u5316\u7387\uff0c\u865a\u90e8");
    model.variable("var1")
         .set("chim_xy_p", "(w*wm*(w0^2-w^2*(1+alpha^2)))/((w0^2-w^2*(1+alpha^2))^2+4*(w0*w*alpha)^2)", "\u78c1\u5316\u7387\uff0c\u5b9e\u90e8");
    model.variable("var1")
         .set("chim_xy_b", "2*w0*wm*w^2*alpha/((w0^2-w^2*(1+alpha^2))^2+4*(w0*w*alpha)^2)", "\u78c1\u5316\u7387\uff0c\u865a\u90e8");
    model.variable("var1").set("chim_xx", "chim_xx_p-j*chim_xx_b", "\u590d\u78c1\u5316\u7387");
    model.variable("var1").set("chim_xy", "chim_xy_b+j*chim_xy_p", "\u590d\u78c1\u5316\u7387");
    model.variable("var1").set("murxx", "(1+chim_xx)", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murxy", "chim_xy", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murxz", "0", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("muryx", "-chim_xy", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("muryy", "murxx", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("muryz", "0", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murzx", "0", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murzy", "0", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murzz", "1", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("tdeltae", "0.0002", "\u6709\u6548\u635f\u8017\u89d2\u6b63\u5207");
    model.variable("var1").set("eps_r_p", "14.5", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u5b9e\u90e8");
    model.variable("var1").set("eps_r_b", "14.5*tdeltae", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u865a\u90e8");
    model.variable("var1").set("eps_r", "eps_r_p-j*eps_r_b", "\u590d\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"0.2-0.1/(3*sqrt(3))", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("size", "0.2/3", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-0.2", "-0.1/3"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").set("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", 120);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy2").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").selection("input").set("copy2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").set("rot", -120);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r1", "rot1", "rot2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "0.2/(3*sqrt(3))");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy3", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy3").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("copy3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot3", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot3").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot3").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy4", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy4").selection("input").set("rot3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca1").set("isotropic", "sc_chamfer");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca1").selection("input").set("copy4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").selection("input")
         .set("sca1", "uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sca2", "Scale");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca2").selection("input").set("rot3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca2").set("isotropic", "sc_ferrite");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sca2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.1/3", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run("fin");

    model.title("\u53c2\u6570\u5316\u73af\u884c\u5668\u51e0\u4f55\u7ed3\u6784");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u5176\u4e2d\u5305\u542b\u201c\u6709\u635f\u94c1\u6c27\u4f53\u4e09\u7aef\u53e3\u73af\u884c\u5668\u7684\u963b\u6297\u5339\u914d\u201d\u6a21\u578b\u7684\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("lossy_circulator_3d_geom.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
