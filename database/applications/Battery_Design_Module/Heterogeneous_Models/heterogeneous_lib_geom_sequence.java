/*
 * heterogeneous_lib_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class heterogeneous_lib_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Heterogeneous_Models");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("s_gap", "1.5[um]", "\u5927\u9897\u7c92\u7684\u7c92\u95f4\u95f4\u9699");
    model.param().set("s_unit_cell", "Rp_large+s_gap/2", "\u57fa\u672c\u5355\u5143\u8fb9\u957f");
    model.param()
         .set("h_offset", "4*s_unit_cell*sqrt(0.5)", "\u6cbf\u7a7f\u5c42\u65b9\u5411\u7684\u7c92\u95f4\u504f\u79fb");
    model.param().set("Q_cell_target", "20[A*h/m^2]", "\u76ee\u6807\u622a\u9762\u5bb9\u91cf");
    model.param().set("csmax_pos", "49000[mol/m^3]", "\u6700\u5927\u9502\u6d53\u5ea6\uff0c\u6b63\u6781\u9897\u7c92");
    model.param()
         .set("solminpos", "0.3", "\u6700\u5c0f\u9502\u5316\u6c34\u5e73 (100% SOC)\uff0c\u6b63\u6781\u9897\u7c92");
    model.param()
         .set("solmaxpos", "0.9", "\u6700\u5927\u9502\u5316\u6c34\u5e73 (0% SOC)\uff0c\u6b63\u6781\u9897\u7c92");
    model.param().set("Rp_large", "7[um]", "\u6b63\u6781\u548c\u77f3\u58a8\u9897\u7c92\u7684\u534a\u5f84");
    model.param()
         .set("Q_layer_pos", "4*Rp_large^3*pi/3/4/s_unit_cell^2*csmax_pos*F_const*(solmaxpos-solminpos)", "\u6bcf\u5c42\u6b63\u6781\u9897\u7c92\u7684\u5bb9\u91cf");
    model.param()
         .set("N_layers_pos", "ceil(Q_cell_target/Q_layer_pos)", "\u6b63\u6781\u9897\u7c92\u7684\u5c42\u6570");
    model.param().set("Q_pos", "N_layers_pos*Q_layer_pos", "\u6b63\u6781\u7684\u6700\u7ec8\u5bb9\u91cf");
    model.param().set("Q_cell", "Q_pos", "\u7535\u6c60\u5bb9\u91cf");
    model.param().set("csmax_Gr", "31507[mol/m^3]", "\u6700\u5927\u9502\u6d53\u5ea6\uff0c\u77f3\u58a8");
    model.param().set("solmin_Gr", "0.0292", "\u6700\u5c0f\u9502\u5316\u6c34\u5e73 (0% SOC)\uff0c\u77f3\u58a8");
    model.param()
         .set("solmax_Gr_target", "0.8", "\u76ee\u6807\u6700\u5927\u9502\u5316\u6c34\u5e73\uff08\u7ea6 100% SOC\uff09\uff0c\u77f3\u58a8");
    model.param()
         .set("Q_layer_Gr", "4*Rp_large^3*pi/3/4/s_unit_cell^2*csmax_Gr*F_const*(solmax_Gr_target-solmin_Gr)", "\u6bcf\u5c42\u77f3\u58a8\u9897\u7c92\u7684\u5bb9\u91cf");
    model.param().set("Rp_Si", "1.5[um]", "\u7845\u9897\u7c92\u534a\u5f84");
    model.param().set("csmax_Si", "278000[mol/m^3]", "\u6700\u5927\u9502\u6d53\u5ea6\uff0c\u7845");
    model.param().set("solmin_Si", "0.0083", "\u6700\u5c0f\u9502\u5316\u6c34\u5e73 (0% SOC)\uff0c\u7845");
    model.param().set("solmax_Si", "1", "\u6700\u5927\u9502\u5316\u6c34\u5e73 (100% SOC)\uff0c\u7845");
    model.param()
         .set("Q_layer_Si", "4*Rp_Si^3*pi/3/4/s_unit_cell^2*csmax_Si*F_const*(solmax_Si-solmin_Si)", "\u6bcf\u5c42\u7845\u9897\u7c92\u7684\u5bb9\u91cf");
    model.param()
         .set("N_layers_neg", "ceil(Q_cell/(Q_layer_Gr+Q_layer_Si))", "\u8d1f\u6781\u9897\u7c92\u7684\u5c42\u6570");
    model.param().set("L_sep", "25[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("L_neg", "2*s_unit_cell+s_gap+(N_layers_neg-1)*h_offset/2", "\u8d1f\u6781\u539a\u5ea6");
    model.param().set("L_pos", "2*s_unit_cell+s_gap+(N_layers_pos-1)*h_offset/2", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("L_cell", "L_neg+L_sep+L_pos", "\u7535\u6c60\u539a\u5ea6");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").inputParam().set("N_layers", "5");
    model.geom("part1").inputParam().descr("N_layers", "Number of particles in through-plane direction");
    model.geom("part1").inputParam().set("Rp", "5[um]");
    model.geom("part1").inputParam().descr("Rp", "Particle radius");
    model.geom("part1").create("sph1", "Sphere");
    model.geom("part1").feature("sph1").set("r", "Rp");
    model.geom("part1").feature("sph1").set("pos", new String[]{"Rp_large+s_gap", "0", "0"});
    model.geom("part1").run("sph1");
    model.geom("part1").create("arr1", "Array");
    model.geom("part1").feature("arr1").selection("input").set("sph1");
    model.geom("part1").feature("arr1").set("fullsize", new String[]{"ceil(N_layers/2)", "1", "1"});
    model.geom("part1").feature("arr1").set("displ", new String[]{"h_offset", "0", "0"});
    model.geom("part1").run("arr1");
    model.geom("part1").create("if1", "If");
    model.geom("part1").feature().createAfter("endif1", "EndIf", "if1");
    model.geom("part1").run("if1");
    model.geom("part1").create("sph2", "Sphere");
    model.geom("part1").feature("sph2").set("r", "Rp");
    model.geom("part1").feature("sph2").set("pos", new String[]{"Rp_large+s_gap+h_offset/2", "0", "0"});
    model.geom("part1").feature("sph2").setIndex("pos", "s_unit_cell", 1);
    model.geom("part1").feature("sph2").setIndex("pos", "s_unit_cell", 2);
    model.geom("part1").run("sph2");
    model.geom("part1").create("arr2", "Array");
    model.geom("part1").feature("arr2").selection("input").set("sph2");
    model.geom("part1").feature("arr2").set("fullsize", new String[]{"floor(N_layers/2)", "1", "1"});
    model.geom("part1").feature("arr2").set("displ", new String[]{"h_offset", "0", "0"});
    model.geom("part1").run("arr2");
    model.geom("part1").run("endif1");
    model.geom("part1").create("uni1", "Union");
    model.geom("part1").feature("uni1").selection("input").set("arr1", "arr2");
    model.geom("part1").run("uni1");
    model.geom("part1").create("blk1", "Block");
    model.geom("part1").feature("blk1").set("size", new String[]{"N_layers*h_offset", "1", "1"});
    model.geom("part1").feature("blk1").setIndex("size", "s_unit_cell", 1);
    model.geom("part1").feature("blk1").setIndex("size", "s_unit_cell", 2);
    model.geom("part1").run("blk1");
    model.geom("part1").create("int1", "Intersection");
    model.geom("part1").feature("int1").selection("input").set("blk1", "uni1");
    model.geom("part1").run("int1");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "N_layers", "N_layers_pos");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Rp", "Rp_large");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"0", "0", "s_unit_cell/2"});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "L_cell");
    model.component("comp1").geom("geom1").feature("mov1").set("selresult", true);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "N_layers", "N_layers_neg");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "Rp", "Rp_large");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("pi2");
    model.component("comp1").geom("geom1").feature("mov2").set("selresult", true);
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "N_layers", "N_layers_neg");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "Rp", "Rp_Si");
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("pi3");
    model.component("comp1").geom("geom1").feature("rot2").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot2").set("pos", new String[]{"0", "s_unit_cell/2", "0"});
    model.component("comp1").geom("geom1").feature("rot2").setIndex("pos", "s_unit_cell/2", 2);
    model.component("comp1").geom("geom1").feature("rot2").set("selresult", true);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L_neg", "s_unit_cell", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "s_unit_cell", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"L_sep", "s_unit_cell", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "s_unit_cell", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"L_neg", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"L_pos", "s_unit_cell", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "s_unit_cell", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"L_neg+L_sep", "0", "0"});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1", "blk3");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("mov2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("mov2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").init();
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("mov1", "mov2", "rot2");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"mov2", "dif1"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("intsel2", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel2").set("input", new String[]{"mov1", "dif1"});
    model.component("comp1").geom("geom1").run("intsel2");
    model.component("comp1").geom("geom1").create("intsel3", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel3").set("input", new String[]{"rot2", "dif1"});
    model.component("comp1").geom("geom1").run("intsel3");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"mov1", "mov2", "rot2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2")
         .set("input", new String[]{"intsel1", "intsel2", "intsel3"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"blk2", "dif1"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "s_unit_cell*0.01");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", "s_unit_cell*0.01");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "s_unit_cell*0.99");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "s_unit_cell*0.99");
    model.component("comp1").geom("geom1").feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "-s_gap/10");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "s_gap/10");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel3", "boxsel2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmin", "L_cell-s_gap/10");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "L_cell+s_gap/10");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"boxsel2", "boxsel3"});

    model.title(null);

    model.description("");

    model.label("heterogeneous_lib_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
