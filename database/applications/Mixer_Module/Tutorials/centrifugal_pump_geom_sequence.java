/*
 * centrifugal_pump_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:13 by COMSOL 6.3.0.290. */
public class centrifugal_pump_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("beta_in", "90[deg]", "\u53f6\u7247\u5165\u89d2");
    model.param().set("beta_out", "10[deg]", "\u53f6\u7247\u51fa\u89d2");
    model.param().set("alfa_in", "90[deg]-beta_in", "\u5185\u90e8\u53f6\u7247\u677f\u89d2\u5ea6");
    model.param().set("alfa_out", "90[deg]-beta_out", "\u5916\u90e8\u53f6\u7247\u677f\u89d2\u5ea6");
    model.param().set("beta", "alfa_out-alfa_in", "\u89d2\u5ea6\u5dee");
    model.param().set("final_angle", "beta+alfa_in", "\u53f6\u7247\u5e73\u9762\u7684\u6700\u5927\u89d2\u5ea6");
    model.param().set("theta", "alfa_out-beta/2", "\u5206\u6bb5\u89d2\u5ea6");
    model.param().set("bl_h", "2[cm]", "\u53f6\u7247\u9ad8\u5ea6");
    model.param().set("c_rad", "5[cm]", "\u5f84\u5411\u5f26\u957f");
    model.param().set("chord", "c_rad/cos(theta)", "\u5f26\u957f");
    model.param().set("d_in", "4[cm]", "\u5185\u5f84");
    model.param()
         .set("d_out", "sqrt((R_constr-th_blade/2)^2+(R_constr-d_in/2-2*th_blade/2)^2-2*(R_constr-th_blade/2)*(R_constr-d_in/2-2*th_blade/2)*cos(beta+alfa_in))*2", "\u53f6\u8f6e\u76f4\u5f84");
    model.param().set("exp_out", "1.0", "\u51fa\u53e3\u901a\u9053\u7684\u81a8\u80c0\u7387");
    model.param().set("exp_spi", ".045", "\u87ba\u65cb\u6307\u6570");
    model.param()
         .set("gap", "5[mm]", "\u677f\u4e0e\u87ba\u65cb\u548c\u65cb\u8f6c\u57df\u6784\u9020\u4e4b\u95f4\u7684\u8ddd\u79bb\u63a7\u5236\u53c2\u6570");
    model.param().set("in_d", "3.5[cm]", "\u5165\u53e3\u901a\u9053\u76f4\u5f84");
    model.param().set("n_b", "7", "\u53f6\u7247\u6570");
    model.param().set("pl_th", "0.4[cm]", "\u677f\u539a");
    model.param().set("R_constr", "chord/(2*sin(beta/2))", "\u6784\u9020\u534a\u5f84");
    model.param().set("sh_d", "2[cm]", "\u8f74\u5f84");
    model.param().set("sh_h", "0.3[cm]", "\u6da1\u58f3\u548c\u677f\u4e0e\u5e95\u90e8\u4e4b\u95f4\u7684\u8ddd\u79bb");
    model.param().set("th_blade", "2[mm]", "\u53f6\u7247\u539a\u5ea6");
    model.param().set("u_c", "1.5[mm]", "\u4e0a\u65b9\u95f4\u9699");
    model.param().set("vol_h", "sh_h+pl_th+bl_h+u_c", "\u6da1\u58f3\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u4e0b\u53f6\u7247\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "sh_h+pl_th");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "d_out/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1").label("\u4e0a\u4fa7");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1").set("parmin", "alfa_in[1/rad]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1").set("parmax", "final_angle[1/rad]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1")
         .set("coord", new String[]{"(R_constr+th_blade/2)*cos(s)", ""});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1")
         .setIndex("coord", "(R_constr+th_blade/2)*sin(s)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1")
         .set("pos", new String[]{"-R_constr+d_in/2+th_blade/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").selection("input").set("pc1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").set("upthick", "th_blade");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("thi1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("int1").selection("input")
         .set("c1", "thi1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("int1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("int1", 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "th_blade/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point").set("fil1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", "th_blade/6");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").set("fil2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1")
         .set("rot", "range(0,360/n_b,360-360/n_b)");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u53f6\u7247\u62c9\u4f38");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "bl_h", 0);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u58c1 2");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").label("\u677f\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "sh_h+pl_th");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "d_out/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("\u677f\u62c9\u4f38");
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "pl_th", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").label("\u8f74");
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "sh_h");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", "sh_d/2");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "sh_h", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").label("\u9525\u5f62\u8f6e\u6bc2");
    model.component("comp1").geom("geom1").feature("wp4").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "pl_th+sh_h", 0, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", "sh_d/1.2", 0, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "bl_h*.9+pl_th+sh_h", 1, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", "sh_d/2", 1, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "bl_h*.9+pl_th+sh_h", 2, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "pl_th+sh_h", 3, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp4");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").feature("rev1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").label("\u8717\u58f3\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pc1").label("\u8717\u58f3");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pc1").set("parmax", "2*pi");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pc1")
         .set("coord", new String[]{"-exp(exp_spi*s)*cos(s)*(d_out*1.01+gap)/2", ""});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pc1")
         .setIndex("coord", "exp(exp_spi*s)*sin(s)*(d_out*1.01+gap)/2", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("pc1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").label("\u51fa\u53e3\u901a\u9053");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "-exp(exp_spi*2*pi)*cos(2*pi)*(d_out*1.01+gap)/2", 0, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "(-exp(exp_spi*2*pi)*cos(2*pi)*(d_out*1.01+gap)/2)*exp_out", 1, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "d_out*0.2", 1, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "-exp(exp_spi*0)*cos(0)*(d_out*1.01+gap)/2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "d_out*0.2", 2, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1")
         .setIndex("table", "-exp(exp_spi*0)*cos(0)*(d_out*1.01+gap)/2", 3, 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("csol1").selection("input")
         .set("pc1", "pol1");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("csol1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("uni1").selection("input").set("csol1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("fil1").selection("point").set("uni1", 3);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("fil1").set("radius", "0.0008");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "vol_h", 0);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").label("\u5165\u53e3\u901a\u9053");
    model.component("comp1").geom("geom1").feature("wp6").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("c1").set("r", "in_d/2");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("c1")
         .set("pos", new String[]{"vol_h+in_d/2-.001", "0"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("c1").setIndex("pos", "in_d/2+.01", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("c1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("size", new String[]{"vol_h*.5", "in_d/2+.01"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("pos", new String[]{"vol_h", "0"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").setIndex("layer", 0.01, 0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pard1").selection("domain").set("r1", 2);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pard1").set("partitionwith", "objects");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pard1").selection("object").set("c1");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("pard1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("extract1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("extract1").selection("input")
         .set("pard1", 1, 2);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("extract1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("uni1").selection("input").set("extract1");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp6").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("fil1").selection("point").set("uni1", 4);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("fil1").set("radius", 0.001);
    model.component("comp1").geom("geom1").feature("wp6").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature().create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev2").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").feature("rev2").set("origfaces", false);
    model.component("comp1").geom("geom1").run("rev2");
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp7").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r1")
         .set("size", new String[]{"d_out/2+gap/2", "1"});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r1").setIndex("size", "pl_th+bl_h+u_c", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r1")
         .set("pos", new String[]{"-(d_out/2+gap/2)", "0"});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r1").setIndex("pos", "sh_h-u_c/2", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r2")
         .set("size", new String[]{"sh_d/2+gap/2", "1"});
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r2").setIndex("size", "sh_h", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("r2")
         .set("pos", new String[]{"-(sh_d/2+gap/2)", "0"});
    model.component("comp1").geom("geom1").feature("wp7").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("wp7");
    model.component("comp1").geom("geom1").feature().create("rev3", "Revolve");
    model.component("comp1").geom("geom1").feature("rev3").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev3").label("\u65cb\u8f6c\u57df 1");
    model.component("comp1").geom("geom1").feature("rev3").set("origfaces", false);
    model.component("comp1").geom("geom1").feature("rev3").set("selresult", true);
    model.component("comp1").geom("geom1").run("rev3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("ext4", "rev3");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").set("extrudefrom", "faces");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").set("rev2", 18, 11, 12, 20);
    model.component("comp1").geom("geom1").feature("ext5").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "2*vol_h", 0);
    model.component("comp1").geom("geom1").feature("ext5").set("crossfaces", false);
    model.component("comp1").geom("geom1").feature("ext5").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext5").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").feature().create("ext6", "Extrude");
    model.component("comp1").geom("geom1").feature("ext6").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext6").selection("inputface").set("dif1", 5);
    model.component("comp1").geom("geom1").feature("ext6").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext6").setIndex("distance", "4*vol_h", 0);
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd1").selection("input").named("ext5");
    model.component("comp1").geom("geom1").feature("cmd1").set("ignoreadj", false);
    model.component("comp1").geom("geom1").run("cmd1");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("cmd1", 103, 105, 108, 174, 175, 177, 196, 197, 198, 199, 200);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("cmd2", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd2").selection("input").set("ige1", 1, 4);
    model.component("comp1").geom("geom1").run("cmd2");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").set("cmd2", 80, 81, 96, 98);
    model.component("comp1").geom("geom1").run("cmf1");
    model.component("comp1").geom("geom1").create("cmf2", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input").set("cmf1", 33, 34, 48, 49);
    model.component("comp1").geom("geom1").run("cmf2");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("cmf2", 1, 2, 3, 10, 11);
    model.component("comp1").geom("geom1").feature("sel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u58c1");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"csel1", "sel1"});

    model.title(null);

    model.description("");

    model.label("centrifugal_pump_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
